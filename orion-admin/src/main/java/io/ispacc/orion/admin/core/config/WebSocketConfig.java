package io.ispacc.orion.admin.core.config;

import io.ispacc.orion.admin.core.constant.WebSocketConstant;
import io.ispacc.orion.admin.core.interceptor.LoginHandshakeInterceptor;
import io.ispacc.orion.admin.core.interceptor.PrincipalHandshakeHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-12 11:04
 */
@AllArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final LoginHandshakeInterceptor loginHandshakeInterceptor;
    private final PrincipalHandshakeHandler principalHandshakeHandler;
    //ChannelInterceptor ImmutableMessageChannelInterceptor
    //WebSocketHandler

    /**
     * 注册了一个名为/websocket的WebSocket端点
     * 并使用withSockJS()方法启用SockJS支持。
     * 这意味着客户端可以通过WebSocket协议与/orion端点进行通信，
     * 但如果客户端浏览器不支持WebSocket或无法直接连接到WebSocket端点，
     * 它会使用SockJS库提供的其他传输选项，例如轮询、长轮询等，以确保与WebSocket端点的通信能够正常进行。
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
//                .addInterceptors(loginHandshakeInterceptor) //拦截器 权限校验
//                .setHandshakeHandler(principalHandshakeHandler)//握手处理器 加入了身份信息 用于一对一聊天使用
                .setAllowedOriginPatterns("*");
    }


    //todo 后续可引入rabbitmq、redis、kafka等消息代理去替换掉内存代理
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //监听客户端消息
        registry.setApplicationDestinationPrefixes("/" + WebSocketConstant.app_destination_prefix);
        //监听服务的消息
        registry.enableSimpleBroker("/" + WebSocketConstant.room_destination_prefix, "/" + WebSocketConstant.user_destination_prefix);
        //设置客户端接收点对点消息地址的前缀
        registry.setUserDestinationPrefix("/" + WebSocketConstant.user_prefix);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new InChannelInterceptor());
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(new OutChannelInterceptor());
    }
}

//可拦截用户监听channel
class InChannelInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        return message;
    }
}

//可拦截服务端发送的消息
class OutChannelInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        return message;
    }
}
