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
    private static final FastThreadLocal<User> threadLocal1 = new FastThreadLocal<>();
    private static final FastThreadLocal<Long> threadLocal2 = new FastThreadLocal<>();

    public static User getUser() {
        return threadLocal1.get();
    }

    public static void setUser(User user) {
        threadLocal1.set(user);
    }

    public static long getUserId() {
        return threadLocal2.get();
    }

    public static void setUserId(Long userId) {
        threadLocal2.set(userId);
    }

    public static void removeUser() {
        threadLocal1.remove();
    }

    public static void removeUserId() {
        threadLocal2.remove();
    }
}
