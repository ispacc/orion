package io.ispacc.orion.admin.module.websocket.service;

/**
 * websocket服务类
 */
public interface WebSocketService {

    /**
     * 给某个用户发送消息
     *
     * @param userId 接收人用户id
     * @param msg    消息内容
     */
    <T> void sendToUser(Long userId, T msg);

    /**
     * 给某个群聊发送消息
     *
     * @param roomId 群聊id
     * @param msg    消息内容
     */
    <T> void sendToRoom(Long roomId, T msg);
}
