package io.ispacc.orion.admin.module.chat.listener;

import io.ispacc.orion.admin.module.chat.dao.MessageDao;
import io.ispacc.orion.admin.module.chat.entity.Message;
import io.ispacc.orion.admin.module.chat.event.MessageSendEvent;
import io.ispacc.orion.admin.module.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 17:18
 */
@Slf4j
@Component
public class MessageSendListener {
    private MessageDao messageDao;
    private WebSocketService webSocketService;

    //给同一群组的用户发送广播消息,如果执行异常无需回滚发布者代码
    @Async
    @TransactionalEventListener(classes = MessageSendEvent.class, fallbackExecution = true)
    public void notifyRoomAllOnlineUser(MessageSendEvent event) {
        Message msg = messageDao.getById(event.getId());
        //webSocketService.sendToRoom(msg.getRoomId(), msg);
    }
}
