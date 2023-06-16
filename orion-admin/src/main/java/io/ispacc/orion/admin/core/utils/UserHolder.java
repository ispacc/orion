package io.ispacc.orion.admin.core.utils;

import io.ispacc.orion.admin.module.admin.entity.User;
import io.netty.util.concurrent.FastThreadLocal;

/**
 * 每次请求,拦截器会根据用户的token,将用户信息存储至线程变量中,以便方法使用
 * 如果请求无需token,则该线程变量值为空,请注意请求设置权限,以防止空指针异常
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 11:12
 */
public class UserHolder {
    private static final FastThreadLocal<User> threadLocal = new FastThreadLocal<>();

    public static void setUser(User user) {
        threadLocal.set(user);
    }

    public static User getUser() {
        return threadLocal.get();
    }

    public static void removeUser() {
        threadLocal.remove();
    }
}
