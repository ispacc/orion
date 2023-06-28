package io.ispacc.orion.admin.module.websocket.service;

import java.util.List;

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
     * 给用户s发送消息
     *
     * @param userIds 接收人用户ids
     * @param msg     消息内容
     */
    <T> void sendToUsers(List<Long> userIds, T msg);

    /**
     * 给某个channel发送消息
     *
     * @param roomId 群组id
     * @param msg    消息内容
     */
    <T> void sendToRoom(Long roomId, T msg);
}
