package io.ispacc.orion.admin.module.chat.service.impl;

import io.ispacc.orion.admin.core.constant.RedisConstant;
import io.ispacc.orion.admin.core.utils.AssertUtil;
import io.ispacc.orion.admin.module.admin.dao.UserDao;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.chat.controller.req.MessageReq;
import io.ispacc.orion.admin.module.chat.controller.resp.RoomResp;
import io.ispacc.orion.admin.module.chat.controller.resp.UserResp;
import io.ispacc.orion.admin.module.chat.dao.MessageDao;
import io.ispacc.orion.admin.module.chat.dao.RoomDao;
import io.ispacc.orion.admin.module.chat.entity.Message;
import io.ispacc.orion.admin.module.chat.entity.Room;
import io.ispacc.orion.admin.module.chat.event.MessageSendEvent;
import io.ispacc.orion.admin.module.chat.service.ChatService;
import io.ispacc.orion.admin.module.chat.service.adapter.MessageAdapter;
import io.ispacc.orion.admin.module.chat.service.adapter.RoomAdapter;
import io.ispacc.orion.admin.module.chat.service.adapter.UserAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
    private final RoomDao roomDao;
    private final UserDao userDao;
    private final MessageDao messageDao;
    private final RedisTemplate<String, String> redisTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public List<RoomResp> getRoomsByUserId(Long userId) {
        List<Room> roomEntityList = roomDao.getByUserId(userId);
        return RoomAdapter.buildResp(roomEntityList);
    }

    @Override
    public List<UserResp> getUsersByRoomId(Long roomId) {
        List<User> userEntityList = userDao.getByRoomId(roomId);
        Set<Long> userIds = userEntityList.stream().map(User::getUserId).collect(Collectors.toSet());
        Set<Long> onlineUserIds = getUsersInOnline(userIds);
        return UserAdapter.buildResp(userEntityList, onlineUserIds);
    }

    @Transactional
    @Override
    public void sendMsgToRoomId(MessageReq messageReq, Long sendUserId) {
        checkMsg(messageReq, sendUserId);
        Message message = MessageAdapter.buildEntity(messageReq, sendUserId);
        messageDao.save(message);
        applicationEventPublisher.publishEvent(new MessageSendEvent(this, message.getId()));
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
    private void checkMsg(MessageReq messageReq, Long sendUserId) {
        AssertUtil.isNotEmpty(messageReq.getContent(), "消息不能为空");
        AssertUtil.isTrue(messageReq.getContent().length() < 500, "消息过长,熊宇航要打人了,兄die");
        Room room = roomDao.getRoomByIdExistsUserId(messageReq.getRoomId(), sendUserId);
        AssertUtil.isTrue(room != null, "发送的聊天室不存在,不要试探我了哥");
        if (Objects.nonNull(messageReq.getReplyMsgId())) {
            AssertUtil.isTrue(messageDao.getByIdExistsRoomId(messageReq.getReplyMsgId(), messageReq.getRoomId()) != null, "回复的消息不存在");
        }
    }

    //todo 后续根据熊宇航的redis module编写情况 可迁移至redis模块
    //todo redisTemplate.opsForSet().isMember(RedisConstant.websocket_online_users, o)后续改成executePipelined,返回null需要评估是否报错让前端刷新
    //将在线的当前用户存储到返回值map中
    //1在线 0离线
    private Set<Long> getUsersInOnline(Set<Long> userId) {
        if (userId.size() < 1) return new HashSet<>();
        //在线人数
        Long onlineSize = redisTemplate.opsForSet().size(RedisConstant.websocket_online_users);
        //如果为null,按照无人在线处理
        if (onlineSize == null) return new HashSet<>();
        //如果在线人数过多的话,根据selectSize循环判断,否则获取所有onlineSize循环判断,节约内存
        if (onlineSize < 500) {
            Set<String> onlineUserIds = redisTemplate.opsForSet().members(RedisConstant.websocket_online_users);
            if (onlineUserIds == null) return new HashSet<>();
            return onlineUserIds.stream().map(Long::parseLong).filter(userId::contains).collect(Collectors.toSet());
        } else {
            return userId.stream().filter(o -> redisTemplate.opsForSet().isMember(RedisConstant.websocket_online_users, o)).collect(Collectors.toSet());
        }
    }

}
