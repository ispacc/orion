package io.ispacc.orion.admin.core.interceptor;

import io.ispacc.orion.admin.core.constant.WebSocketConstant;
import io.ispacc.orion.admin.module.admin.dao.UserDao;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Slf4j
@SuppressWarnings("NullableProblems")
public class LoginHandshakeInterceptor implements HandshakeInterceptor {

    private final UserDao userDao;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        //todo 对握手的请求进行权限校验，并保存请求用户信息
        // TODO 暂不修改
        Long userId = null;
        String[] queryParams = request.getURI().getQuery().split("&");
        for (String param : queryParams) {
            String[] keyValue = param.split("=");
            String key = keyValue[0];
            String value = keyValue[1];

            if ("userId".equals(key)) {
                userId = Long.valueOf(value);
                break;
            }
        }
        attributes.put(WebSocketConstant.websocket_connect_user, userDao.getById(userId).getUserId().toString());
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
