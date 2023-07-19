package io.ispacc.orion.chat.core.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import io.ispacc.orion.chat.core.constant.WebSocketConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * 建立websocket连接前进行权限校验 连接后进行异常处理
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-14 20:14
 */
@Component
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("NullableProblems")
public class LoginHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // 未登录情况下拒绝握手
        if (!StpUtil.isLogin()) {
            return false;
        }

        // 标记 userId，握手成功
        attributes.put(WebSocketConstant.websocket_connect_user, StpUtil.getLoginIdAsLong());
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        //todo 给前端返回连接结果
        if (exception != null) {
            //连接异常
            log.error("【websocket连接异常】" + exception);
        }
        //正常连接
    }
}
