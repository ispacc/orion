package io.ispacc.orion.admin.module.chat.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.ispacc.orion.admin.core.constant.CacheConsts;
import io.ispacc.orion.admin.core.utils.AssertUtil;
import io.ispacc.orion.admin.module.admin.dao.UserDao;
import io.ispacc.orion.admin.module.admin.dao.UserFriendDao;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.admin.entity.UserFriend;
import io.ispacc.orion.admin.module.chat.controller.req.RoomMessageReq;
import io.ispacc.orion.admin.module.chat.controller.req.UserMessageReq;
import io.ispacc.orion.admin.module.chat.controller.resp.RoomResp;
import io.ispacc.orion.admin.module.chat.controller.resp.UserFriendResp;
import io.ispacc.orion.admin.module.chat.controller.resp.msg.RoomMsgResp;
import io.ispacc.orion.admin.module.chat.controller.resp.msg.UserMsgResp;
import io.ispacc.orion.admin.module.chat.controller.resp.user.RoomUserResp;
import io.ispacc.orion.admin.module.chat.dao.MessageDao;
import io.ispacc.orion.admin.module.chat.dao.RoomDao;
import io.ispacc.orion.admin.module.chat.dao.UserRoomDao;
import io.ispacc.orion.admin.module.chat.entity.Message;
import io.ispacc.orion.admin.module.chat.entity.Room;
import io.ispacc.orion.admin.module.chat.event.RoomMessageRemoveUserEvent;
import io.ispacc.orion.admin.module.chat.event.RoomMessageSendEvent;
import io.ispacc.orion.admin.module.chat.event.UserMessageSendEvent;
import io.ispacc.orion.admin.module.chat.service.ChatService;
import io.ispacc.orion.admin.module.chat.service.adapter.MessageAdapter;
import io.ispacc.orion.admin.module.chat.service.adapter.RoomAdapter;
import io.ispacc.orion.admin.module.chat.service.adapter.UserAdapter;
import io.ispacc.orion.admin.module.chat.service.adapter.UserFriendAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 消息处理类
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:28
 */
@AllArgsConstructor
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    private final UserFriendDao userFriendDao;
    private final RoomDao roomDao;
    private final UserDao userDao;
    private final MessageDao messageDao;
    private final RedisTemplate<String, String> redisTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserRoomDao userRoomDao;

    @Override
    public List<RoomResp> getRoomsByUserId(Long userId) {
        List<Room> roomEntityList = roomDao.getByUserId(userId);
        return RoomAdapter.buildResp(roomEntityList);
    }

    @Override
    public List<UserFriendResp> getFriendsByUserId(Long userId) {
        List<UserFriend> userFriend = userFriendDao.getUserFriendIdsByUserId(userId);
        if (CollectionUtil.isEmpty(userFriend)) return new ArrayList<>();
        Set<Long> friendIds = userFriend.stream().map(UserFriend::getFriendUserId).collect(Collectors.toSet());
        Set<Long> usersInOnline = getUsersInOnline(friendIds);
        List<User> users = userDao.listByIds(friendIds);
        return UserFriendAdapter.buildFriendResp(userFriend, usersInOnline, users);
    }

    @Override
    public List<RoomUserResp> getUsersByRoomId(Long roomId) {
        Room room = roomDao.getById(roomId);
        if (room == null) return new ArrayList<>();
        List<User> userEntityList = userDao.getByRoomId(roomId);
        Set<Long> userIds = userEntityList.stream().map(User::getUserId).collect(Collectors.toSet());
        Set<Long> onlineUserIds = getUsersInOnline(userIds);

        return UserAdapter.buildResp(userEntityList, onlineUserIds, room.getManageUserId());
    }

    @Transactional
    @Override
    public Long sendMsgToRoomId(RoomMessageReq messageReq, Long sendUserId) {
        checkMsg(messageReq, sendUserId);
        Message message = MessageAdapter.buildEntity(messageReq, sendUserId);
        messageDao.save(message);
        applicationEventPublisher.publishEvent(new RoomMessageSendEvent(this, message.getId()));
        return message.getId();
    }

    @Transactional
    @Override
    public Long sendMsgToUserId(UserMessageReq messageReq, Long sendUserId) {
        checkMsg(messageReq, sendUserId);
        Message message = MessageAdapter.buildEntity(messageReq, sendUserId);
        messageDao.save(message);
        applicationEventPublisher.publishEvent(new UserMessageSendEvent(this, message.getId()));
        return message.getId();
    }

    @Override
    public List<RoomMsgResp> getPageMsgByRoomId(Long msgId, Long roomId) {
        List<Message> messages = messageDao.getPageMsgByRoomId(msgId, roomId);
        if (messages.size() < 1) return new ArrayList<>();
        List<User> users = getUserByMessageList(messages);
        return MessageAdapter.buildRoomMsgResp(messages, users);
    }

    @Override
    public List<UserMsgResp> getPageMsgByFriendId(Long friendId, Long msgId) {
        List<Message> messages = messageDao.getPageMsgByFriendId(msgId, friendId);
        if (messages.size() < 1) return new ArrayList<>();
        List<User> users = getUserByMessageList(messages);
        return MessageAdapter.buildUserMsgResp(messages, users);
    }

    @Transactional
    @Override
    public void removeUserByRoomId(Long roomId, Long currentUserId, Long userId) {
        checkRemoveUserByRoomId(roomId, currentUserId, userId);
        userRoomDao.removeUser(userId, roomId);
        applicationEventPublisher.publishEvent(new RoomMessageRemoveUserEvent(this, roomId, currentUserId, userId));
    }

    private List<User> getUserByMessageList(List<Message> messages) {
        Set<Long> userIds = messages.stream().map(Message::getUserId).collect(Collectors.toSet());
        return userDao.listByIds(userIds);
    }

    /**
     * 检查群聊移除用户是否可行
     * 1、群聊存在
     * 2、用户存在群聊
     * 3、用户是管理员
     *
     * @param roomId 群聊id
     * @param userId 用户id
     */
    private void checkRemoveUserByRoomId(Long roomId, Long currentUserId, Long userId) {
        Room room = roomDao.getById(roomId);
        AssertUtil.isNotNull(room, "群聊不存在");
        AssertUtil.isTrue(Objects.equals(room.getManageUserId(), currentUserId), "你不是群聊的管理员,无法移除");
        AssertUtil.isTrue(userRoomDao.existsUserInRoom(userId, roomId), "用户已经不在群聊中了");
    }

    /**
     * 检查消息是否合法 不合法直接抛出异常
     * 1、消息不能为空
     * 2、消息不能超过500个字符
     * 3、消息发送的群组要存在
     * 5、用户在该群组里
     * 4、回复消息的时候，回复的消息要存在并且在一个群组
     *
     * @param messageReq 消息内容
     */
    private void checkMsg(RoomMessageReq messageReq, Long sendUserId) {
        Room room = roomDao.getRoomByIdExistsUserId(messageReq.getRoomId(), sendUserId);
        AssertUtil.isTrue(room != null, "发送的聊天室不存在,不要试探我了哥");
        if (Objects.nonNull(messageReq.getReplyMsgId())) {
            AssertUtil.isTrue(messageDao.getByIdExistsRoomId(messageReq.getReplyMsgId(), messageReq.getRoomId()) != null, "回复的消息不存在");
        }
    }

    private void checkMsg(UserMessageReq messageReq, Long sendUserId) {
        AssertUtil.isTrue(userFriendDao.countByUserIdAndUserFriendId(sendUserId, messageReq.getFriendUserId()) > 0, "当前用户不是你的好友");
        if (Objects.nonNull(messageReq.getReplyMsgId())) {
            AssertUtil.isTrue(messageDao.getByIdExistsRoomId(messageReq.getReplyMsgId(), null) != null, "回复的消息不存在");
        }
    }

    //todo 后续根据熊宇航的redis module编写情况 可迁移至redis模块
    //todo redisTemplate.opsForSet().isMember(RedisConstant.websocket_online_users, o)后续改成executePipelined,返回null需要评估是否报错让前端刷新
    //将在线的当前用户存储到返回值map中
    //1在线 0离线
    private Set<Long> getUsersInOnline(Set<Long> userId) {
        if (userId.size() < 1) return new HashSet<>();
        //在线人数
        Long onlineSize = redisTemplate.opsForSet().size(CacheConsts.WEBSOCKET_ONLINE_USERS_NAME);
        //如果为null,按照无人在线处理
        if (onlineSize == null) return new HashSet<>();
        //如果在线人数过多的话,根据selectSize循环判断,否则获取所有onlineSize循环判断,节约内存
        if (onlineSize < 500) {
            Set<String> onlineUserIds = redisTemplate.opsForSet().members(CacheConsts.WEBSOCKET_ONLINE_USERS_NAME);
            if (onlineUserIds == null) return new HashSet<>();
            return onlineUserIds.stream().map(Long::parseLong).filter(userId::contains).collect(Collectors.toSet());
        } else {
            return userId.stream().filter(o -> redisTemplate.opsForSet().isMember(CacheConsts.WEBSOCKET_ONLINE_USERS_NAME, o)).collect(Collectors.toSet());
        }
    }
}
