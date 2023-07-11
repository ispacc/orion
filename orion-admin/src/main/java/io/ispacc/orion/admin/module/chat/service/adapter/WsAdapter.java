package io.ispacc.orion.admin.module.chat.service.adapter;

import io.ispacc.orion.admin.core.constant.ChatConstant;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.chat.controller.resp.RoomRemoveUserResp;
import io.ispacc.orion.admin.module.chat.controller.resp.WsBaseResp;
import io.ispacc.orion.admin.module.chat.controller.resp.msg.RoomMsgResp;
import io.ispacc.orion.admin.module.chat.controller.resp.msg.UserMsgResp;
import io.ispacc.orion.admin.module.chat.entity.Message;
import org.springframework.beans.BeanUtils;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-25 09:37
 */
public class WsAdapter {
    public static WsBaseResp<RoomMsgResp> buildRoomMsg(Message msg, User user) {
        RoomMsgResp roomMsgResp = new RoomMsgResp();
        BeanUtils.copyProperties(msg, roomMsgResp);
        roomMsgResp.setUserInfo(UserAdapter.buildResp(user));
        return new WsBaseResp<>(ChatConstant.MsgType.SEND_ROOM_MSG.getType(), roomMsgResp);
    }

    public static WsBaseResp<UserMsgResp> buildUserMsg(Message msg, User user) {
        UserMsgResp userMsgResp = new UserMsgResp();
        BeanUtils.copyProperties(msg, userMsgResp);
        userMsgResp.setUserInfo(UserAdapter.buildResp(user));
        return new WsBaseResp<>(ChatConstant.MsgType.SEND_USER_MSG.getType(), userMsgResp);
    }

    public static WsBaseResp<RoomRemoveUserResp> buildUserMsg(Long roomId, User manager, User removeUser) {
        RoomRemoveUserResp resp = new RoomRemoveUserResp();
        resp.setRoomId(roomId);
        resp.setManagerUser(UserAdapter.buildResp(manager));
        resp.setRemoveUser(UserAdapter.buildResp(removeUser));
        return new WsBaseResp<>(ChatConstant.MsgType.ROOM_REMOVE_USER_MSG.getType(), resp);
    }
}
