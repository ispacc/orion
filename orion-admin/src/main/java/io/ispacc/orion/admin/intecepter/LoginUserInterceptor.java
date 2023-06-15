package io.ispacc.orion.admin.intecepter;

import io.ispacc.orion.admin.module.admin.dao.UserDao;
import io.ispacc.orion.admin.utils.LoginUserRequestHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截所有请求,并设置当前用户至线程变量,如果无则为null
 * 请将该拦截器置于权限拦截器之后
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 11:23
 */

@AllArgsConstructor
@Component
@Order(9)
public class LoginUserInterceptor implements HandlerInterceptor {

    private final UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo 1获取token

        //todo 2根据token获取当前用户信息

        LoginUserRequestHolder.set(userDao.getById(1667116738221449218L));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserRequestHolder.remove();
    }
}
