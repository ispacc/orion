package io.ispacc.orion.admin.module.websocket.listener;

import io.ispacc.orion.admin.core.constant.RedisConstant;
import io.ispacc.orion.admin.core.constant.WebSocketConstant;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.util.Map;

/**
 * websocket事件监听
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-12 12:41
 */

@Component
@Log4j2
@AllArgsConstructor
public class WebSocketEventListener {
    private final RedisTemplate<String, String> redisTemplate;

    //监听连接成功事件 存储在线用户 todo 可记录日志
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        MessageHeaders headers = event.getMessage().getHeaders();
        String userId = getUserIdConn(headers);
        String sessionId = getSessionId(headers);
        if (userId == null) {
            return;
        }
        redisTemplate.opsForSet().add(RedisConstant.websocket_online_users, userId);
    }

    //监听连接断开事件 删除在线用户
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        MessageHeaders headers = event.getMessage().getHeaders();
        String userId = getUserIdDisConn(headers);
        String sessionId = getSessionId(headers);
        if (userId == null) {
            return;
        }
        redisTemplate.opsForSet().remove(RedisConstant.websocket_online_users, userId);
        //todo 循环发送事件,退出群组,告诉好友,俺不在线
    }

    @EventListener
    public void handleWebSocketSessionSubscribeEvent(SessionSubscribeEvent event) {
    }

    @EventListener
    public void handleWebSocketSessionUnsubscribeEvent(SessionUnsubscribeEvent event) {
    }

    private String getUserIdConn(MessageHeaders headers) {
        GenericMessage<?> simpConnectMessage = headers.get(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER, GenericMessage.class);
        if (simpConnectMessage == null) {
            return null;
        }
        MessageHeaders messageHeaders = simpConnectMessage.getHeaders();
        Map<?, ?> map = messageHeaders.get(SimpMessageHeaderAccessor.SESSION_ATTRIBUTES, Map.class);
        if (map == null) {
            return null;
        }
        return (String) map.get(WebSocketConstant.websocket_connect_user);
    }

    private String getUserIdDisConn(MessageHeaders headers) {
        Map<?, ?> map = headers.get(SimpMessageHeaderAccessor.SESSION_ATTRIBUTES, Map.class);
        if (map == null) {
            return null;
        }
        return (String) map.get(WebSocketConstant.websocket_connect_user);
    }

    private String getSessionId(MessageHeaders headers) {
        return headers.get(SimpMessageHeaderAccessor.SESSION_ID_HEADER, String.class);
    }
}
