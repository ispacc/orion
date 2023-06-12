package io.ispacc.orion.admin.module.websocket.controller;

import io.ispacc.orion.admin.module.websocket.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-12 12:39
 */

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        return chatMessage;
    }
}
