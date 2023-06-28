package io.ispacc.orion.admin.core.constant;

/**
 * websocket静态默认值
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-14 15:55
 */
public class WebSocketConstant {
    //点对点消息管道
    public static final String user_destination = "userDestination";

    //群聊消息管道
    public static final String room_destination = "roomDestination";

    //群聊消息前缀
    public static final String room_destination_prefix = "topic";

    //点对点消息前缀
    public static final String user_destination_prefix = "queue";

    //客户端接收点对点消息前缀
    public static final String user_prefix = "user";
    //连接消息前缀
    public static final String app_destination_prefix = "app";

    //连接时身份信息标识
    public static final String websocket_connect_user = "websocketConnectUser";

    public static final String user_channel = "/" + user_destination_prefix + "/" + user_destination;

    public static final String room_channel_prefix = "/" + room_destination_prefix + "/" + room_destination + "/";
}
