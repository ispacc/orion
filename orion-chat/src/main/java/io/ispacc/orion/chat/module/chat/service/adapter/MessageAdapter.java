package io.ispacc.orion.chat.module.chat.service.adapter;

import cn.hutool.core.bean.BeanUtil;
import io.ispacc.orion.chat.module.admin.entity.User;
import io.ispacc.orion.chat.module.chat.controller.req.RoomMessageReq;
import io.ispacc.orion.chat.module.chat.controller.req.UserMessageReq;
import io.ispacc.orion.chat.module.chat.controller.resp.msg.RoomMsgResp;
import io.ispacc.orion.chat.module.chat.controller.resp.msg.UserMsgResp;
import io.ispacc.orion.chat.module.chat.controller.resp.user.BaseUserResp;
import io.ispacc.orion.chat.module.chat.entity.Message;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 16:06
 */
public class MessageAdapter {
    public static Message buildEntity(RoomMessageReq messageReq, Long userId) {
        Message message = new Message();
        BeanUtil.copyProperties(messageReq, message);
        message.setUserId(userId);
        return message;
    }

    public static Message buildEntity(UserMessageReq messageReq, Long userId) {
        Message message = new Message();
        BeanUtil.copyProperties(messageReq, message);
        message.setUserId(userId);
        return message;
    }

    public static List<RoomMsgResp> buildRoomMsgResp(List<Message> messages, List<User> userInfos) {
        List<RoomMsgResp> roomMsgResp = BeanUtil.copyToList(messages, RoomMsgResp.class);
        Map<Long, User> userIdMap = userInfos.stream().collect(Collectors.toMap(User::getUserId, u -> u));
        for (RoomMsgResp resp : roomMsgResp) {
            User user = userIdMap.get(resp.getUserId());
            BaseUserResp userResp = UserAdapter.buildResp(user);
            resp.setUserInfo(userResp);
        }
        return roomMsgResp;
    }

    public static List<UserMsgResp> buildUserMsgResp(List<Message> messages, List<User> userInfos) {
        List<UserMsgResp> roomMsgResp = BeanUtil.copyToList(messages, UserMsgResp.class);
        Map<Long, User> userIdMap = userInfos.stream().collect(Collectors.toMap(User::getUserId, u -> u));
        for (UserMsgResp resp : roomMsgResp) {
            User user = userIdMap.get(resp.getUserId());
            BaseUserResp userResp = UserAdapter.buildResp(user);
            resp.setUserInfo(userResp);
        }
        return roomMsgResp;
    }
}
