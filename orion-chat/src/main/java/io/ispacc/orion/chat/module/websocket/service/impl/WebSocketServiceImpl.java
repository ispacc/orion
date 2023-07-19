package io.ispacc.orion.chat.module.websocket.service.impl;

import io.ispacc.orion.chat.core.constant.WebSocketConstant;
import io.ispacc.orion.chat.module.websocket.service.WebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class WebSocketServiceImpl implements WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public <T> void sendToUser(Long userId, T msg) {
        simpMessagingTemplate.convertAndSendToUser(userId.toString(), WebSocketConstant.user_channel, msg);
    }

    @Override
    public <T> void sendToUsers(List<Long> userIds, T msg) {
        //todo 后改为线程池异步执行,使用CompletableFuture
        for (Long userId : userIds) {
            sendToUser(userId, msg);
        }
    }

    @Override
    public <T> void sendToRoom(Long roomId, T msg) {
        simpMessagingTemplate.convertAndSend(WebSocketConstant.room_channel_prefix + roomId.toString(), msg);
    }
}
