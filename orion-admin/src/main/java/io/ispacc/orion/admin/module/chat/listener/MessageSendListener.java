package io.ispacc.orion.admin.module.chat.listener;

import io.ispacc.orion.admin.module.chat.dao.MessageDao;
import io.ispacc.orion.admin.module.chat.dao.UserRoomDao;
import io.ispacc.orion.admin.module.chat.entity.Message;
import io.ispacc.orion.admin.module.chat.event.RoomMessageSendEvent;
import io.ispacc.orion.admin.module.chat.event.UserMessageSendEvent;
import io.ispacc.orion.admin.module.chat.service.adapter.WsAdapter;
import io.ispacc.orion.admin.module.websocket.service.WebSocketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    //给同一群组的用户发送广播消息,如果执行异常无需回滚发布者代码
    @Async
    @TransactionalEventListener(classes = RoomMessageSendEvent.class, fallbackExecution = true)
    public void notifyRoomAllOnlineUser(RoomMessageSendEvent event) {
        Message msg = messageDao.getById(event.getId());
        List<Long> userIds = userRoomDao.getUserIdByRoomId(msg.getRoomId());
        userIds.remove(msg.getUserId());
        webSocketService.sendToUsers(userIds, WsAdapter.buildRoomMsg(msg));
    }

    @Async
    @TransactionalEventListener(classes = UserMessageSendEvent.class, fallbackExecution = true)
    public void notifyRoomAllOnlineUser(UserMessageSendEvent event) {
        Message msg = messageDao.getById(event.getId());
        webSocketService.sendToUser(msg.getFriendUserId(), WsAdapter.buildUserMsg(msg));
    }
}
