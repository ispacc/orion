package io.ispacc.orion.admin.config;

import io.ispacc.orion.admin.module.websocket.model.ChatMessage;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-12 11:04
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册了一个名为/orion的WebSocket端点
     * 并使用withSockJS()方法启用SockJS支持。
     * 这意味着客户端可以通过WebSocket协议与/orion端点进行通信，
     * 但如果客户端浏览器不支持WebSocket或无法直接连接到WebSocket端点，
     * 它会使用SockJS库提供的其他传输选项，例如轮询、长轮询等，以确保与WebSocket端点的通信能够正常进行。
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/orion").withSockJS();
    }

    //todo 后续可引入rabbitmq、redis、kafka等消息代理去替换掉内存代理
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //监听客户端消息
        registry.setApplicationDestinationPrefixes("/app");
        //监听服务的消息
        registry.enableSimpleBroker("/topic");
    }
}
