package io.ispacc.orion.admin.core.constant;

/**
 * redis的静态默认值
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 16:38
 */
public class CacheConsts {

    //websocket 每连接一个用户 将用户id存储至redis中、断开连接后、删除该值
    public static final String WEBSOCKET_ONLINE_USERS_NAME = "websocket.online.users";

}
