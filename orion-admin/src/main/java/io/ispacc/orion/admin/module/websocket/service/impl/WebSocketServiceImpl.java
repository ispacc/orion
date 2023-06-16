package io.ispacc.orion.admin.module.websocket.service.impl;

import io.ispacc.orion.admin.core.constant.WebSocketConstant;
import io.ispacc.orion.admin.module.websocket.service.WebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WebSocketServiceImpl implements WebSocketService {

    private final SimpMessageSendingOperations sendingOperations;

    @Override
    public <T> void sendToUser(Long userId, T msg) {
        sendingOperations.convertAndSendToUser(userId.toString(), "/" + WebSocketConstant.user_destination_prefix + "/" + WebSocketConstant.user_destination, msg);
    }

    @Override
    public <T> void sendToRoom(Long roomId, T msg) {
        sendingOperations.convertAndSend("/" + WebSocketConstant.room_destination_prefix + "/" + WebSocketConstant.room_destination + "/" + roomId.toString(), msg);
    }
}
