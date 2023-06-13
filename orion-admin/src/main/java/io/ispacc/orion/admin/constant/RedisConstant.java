package io.ispacc.orion.admin.constant;

/**
 * redis的静态默认值
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 16:38
 */
public class RedisConstant {

    //websocket 每连接一个用户 将用户id存储至redis中、断开连接后、删除该值
    public static final String websocket_online_users = "websocket.online.users";
}
