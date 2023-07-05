package io.ispacc.orion.admin.module.chat.service.adapter;

import io.ispacc.orion.admin.core.constant.ChatConstant;
import io.ispacc.orion.admin.module.chat.controller.resp.RoomMsgResp;
import io.ispacc.orion.admin.module.chat.controller.resp.WsBaseResp;
import io.ispacc.orion.admin.module.chat.entity.Message;
import org.springframework.beans.BeanUtils;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-25 09:37
 */
public class WsAdapter {
    public static WsBaseResp<RoomMsgResp> buildRoomMsg(Message msg) {
        RoomMsgResp roomMsgResp = new RoomMsgResp();
        BeanUtils.copyProperties(msg, roomMsgResp);
        return new WsBaseResp<>(ChatConstant.MsgType.SEND_ROOM_MSG.getType(), roomMsgResp);
    }
}
