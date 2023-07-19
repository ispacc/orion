package io.ispacc.orion.chat.module.chat.listener;

import io.ispacc.orion.chat.module.admin.dao.UserDao;
import io.ispacc.orion.chat.module.admin.entity.User;
import io.ispacc.orion.chat.module.chat.dao.MessageDao;
import io.ispacc.orion.chat.module.chat.dao.UserRoomDao;
import io.ispacc.orion.chat.module.chat.entity.Message;
import io.ispacc.orion.chat.module.chat.event.RoomMessageRemoveUserEvent;
import io.ispacc.orion.chat.module.chat.event.RoomMessageSendEvent;
import io.ispacc.orion.chat.module.chat.event.UserMessageSendEvent;
import io.ispacc.orion.chat.module.chat.service.adapter.WsAdapter;
import io.ispacc.orion.chat.module.websocket.service.WebSocketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 17:18
 */
@Slf4j
@AllArgsConstructor
@Component
public class MessageSendListener {
    private final MessageDao messageDao;
    private final WebSocketService webSocketService;
    private final UserRoomDao userRoomDao;
    private final UserDao userDao;
    private final RedisTemplate<String, String> redisTemplate;

    //给同一群组的用户发送广播消息,如果执行异常无需回滚发布者代码
    @Async
    @TransactionalEventListener(classes = RoomMessageSendEvent.class, fallbackExecution = true)
    public void notifyRoomAllOnlineUser(RoomMessageSendEvent event) {
        Message msg = messageDao.getById(event.getId());
        List<Long> userIds = userRoomDao.getUserIdByRoomId(msg.getRoomId());
        userIds.remove(msg.getUserId());
        User user = userDao.getById(msg.getUserId());
        webSocketService.sendToUsers(userIds, WsAdapter.buildRoomMsg(msg, user));
    }

    @Async
    @TransactionalEventListener(classes = UserMessageSendEvent.class, fallbackExecution = true)
    public void notifyRoomAllOnlineUser(UserMessageSendEvent event) {
        Message msg = messageDao.getById(event.getId());
        User user = userDao.getById(msg.getUserId());
        webSocketService.sendToUser(msg.getFriendUserId(), WsAdapter.buildUserMsg(msg, user));
    }

    @Async
    @TransactionalEventListener(classes = RoomMessageRemoveUserEvent.class, fallbackExecution = true)
    public void notifyRoomRemoveUser(RoomMessageRemoveUserEvent event) {
        List<Long> userIds = userRoomDao.getUserIdByRoomId(event.getRoomId());
        User manageUser = userDao.getById(event.getManageUserId());
        User removeUser = userDao.getById(event.getRemoveUserId());
        webSocketService.sendToUsers(userIds, WsAdapter.buildUserMsg(event.getRoomId(), manageUser, removeUser));
    }
}
