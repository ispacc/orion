package io.ispacc.orion.chat.module.chat.service.adapter;

import io.ispacc.orion.chat.module.admin.entity.User;
import io.ispacc.orion.chat.module.admin.entity.UserFriend;
import io.ispacc.orion.chat.module.chat.controller.resp.UserFriendResp;
import io.ispacc.orion.chat.module.chat.controller.resp.user.UserResp;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-06 09:22
 */
public class UserFriendAdapter {
    public static List<UserFriendResp> buildFriendResp(List<UserFriend> userFriends, Set<Long> onlineUserId, List<User> users) {
        List<UserFriendResp> userFriendRespList = userFriends.stream().map(entity -> {
            UserFriendResp userFriendResp = new UserFriendResp();
            BeanUtils.copyProperties(entity, userFriendResp);
            return userFriendResp;
        }).toList();
        List<UserResp> userResp = UserAdapter.buildResp(users, onlineUserId);
        Map<Long, UserResp> userIdMap = userResp.stream().collect(Collectors.toMap(UserResp::getUserId, u -> u));
        for (UserFriendResp userFriendResp : userFriendRespList) {
            UserResp userRespTemp = userIdMap.get(userFriendResp.getFriendUserId());
            userFriendResp.setFriendUser(userRespTemp);
        }
        return userFriendRespList;
    }
}
