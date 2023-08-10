package io.ispacc.orion.chat.module.chat.service;

import io.ispacc.orion.chat.module.chat.controller.req.RoomMessageReq;
import io.ispacc.orion.chat.module.chat.controller.req.UserMessageReq;
import io.ispacc.orion.chat.module.chat.controller.resp.RoomResp;
import io.ispacc.orion.chat.module.chat.controller.resp.UserFriendResp;
import io.ispacc.orion.chat.module.chat.controller.resp.msg.RoomMsgResp;
import io.ispacc.orion.chat.module.chat.controller.resp.msg.UserMsgResp;
import io.ispacc.orion.chat.module.chat.controller.resp.user.RoomUserResp;

import java.util.List;

public interface ChatService {
    /**
     * 根据用户id获取群组信息
     *
     * @param userId 用户id
     * @return 用户加入的群组信息
     */
    List<RoomResp> getRoomsByUserId(Long userId);

    List<UserFriendResp> getFriendsByUserId(Long userId);

    /**
     * 查询组中用户id,并判断用户是否在线
     *
     * @param roomId 群聊id
     * @return 用户信息
     */
    List<RoomUserResp> getUsersByRoomId(Long roomId);

    /**
     * 给群组发送消息
     *
     * @param messageReq 消息信息
     */
    Long sendMsgToRoomId(RoomMessageReq messageReq, Long sendUserId);

    /**
     * 给群组发送消息
     *
     * @param messageReq 消息信息
     */
    Long sendMsgToUserId(UserMessageReq messageReq, Long sendUserId);

    List<RoomMsgResp> getPageMsgByRoomId(Long msgId, Long roomId);

    List<UserMsgResp> getPageMsgByFriendId(Long friendId, Long msgId);

    void removeUserByRoomId(Long roomId, Long currentUserId, Long userId);
}
