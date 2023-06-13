package io.ispacc.orion.admin.utils;

import io.ispacc.orion.admin.module.admin.entity.User;

/**
 * 每次请求,拦截器会根据用户的token,将用户信息存储至线程变量中,以便方法使用
 * 如果请求无需token,则该线程变量值为空,请注意请求设置权限,以防止空指针异常
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 11:12
 */
public class LoginUserRequestHolder {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void set(User user) {
        threadLocal.set(user);
    }

    public static User get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
