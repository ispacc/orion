package io.ispacc.orion.admin.core.interceptor;

import io.ispacc.orion.admin.core.constant.SystemConfigConsts;
import io.ispacc.orion.admin.core.utils.JwtUtils;
import io.ispacc.orion.admin.core.utils.UserHolder;
import io.ispacc.orion.admin.module.admin.dao.UserDao;
import io.ispacc.orion.admin.module.admin.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

/**
 * 拦截所有请求,并设置当前用户至线程变量,如果无则为null
 * 请将该拦截器置于权限拦截器之后
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 11:23
 */

@RequiredArgsConstructor
@Component
@Order(9)
@Slf4j
public class LoginUserInterceptor implements HandlerInterceptor {

    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    @Value("${orion.jwt.tokenHead}")
    private String tokenHead;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = Optional.ofNullable(request.getHeader(SystemConfigConsts.HTTP_AUTH_HEADER_NAME))
                .filter(h -> h.startsWith(tokenHead))
                .map(h -> h.substring(tokenHead.length()))
                .orElse(null);

        if (StringUtils.hasText(token)) {
            Long userId = jwtUtils.parseToken(token, SystemConfigConsts.ORION_USER_KEY);
            if (userId != null) {
                User user = userDao.getById(userId);
                if (user != null) {
                    UserHolder.setUser(user);
                    return true;
                }
            }
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
