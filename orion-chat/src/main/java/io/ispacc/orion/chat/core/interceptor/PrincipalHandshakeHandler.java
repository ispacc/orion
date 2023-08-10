package io.ispacc.orion.chat.core.interceptor;

import io.ispacc.orion.chat.core.constant.WebSocketConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * 用于重写 websocket的认证角色,为每个连接设置用户信息
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-15 14:52
 */
@Component
@SuppressWarnings("NullableProblems")
public class PrincipalHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        //根据token为连接请求设置用户信息
        Long userId = (Long) attributes.get(WebSocketConstant.websocket_connect_user);
        if (userId == null) {
            return null;
        }
        return new WebSocketPrincipal(userId.toString());
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class WebSocketPrincipal implements Principal {
    private String name;

    @Override
    public String getName() {
        return name;
    }
}
