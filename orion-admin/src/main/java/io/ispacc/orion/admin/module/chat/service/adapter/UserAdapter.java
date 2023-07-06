package io.ispacc.orion.admin.module.chat.service.adapter;

import cn.hutool.core.bean.BeanUtil;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.chat.controller.resp.user.BaseUserResp;
import io.ispacc.orion.admin.module.chat.controller.resp.user.RoomUserResp;
import io.ispacc.orion.admin.module.chat.controller.resp.user.UserResp;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 18:09
 */
public class UserAdapter {
    public static List<RoomUserResp> buildResp(List<User> list, Set<Long> onlineUserId, Long managerId) {
        return list.stream()
                .map(a -> {
                    RoomUserResp resp = new RoomUserResp();
                    BeanUtil.copyProperties(a, resp);
                    if (onlineUserId.contains(a.getUserId())) resp.setOnline(1);
                    else resp.setOnline(0);

                    if (Objects.equals(managerId, a.getUserId())) resp.setManager(1);
                    else resp.setManager(0);
                    return resp;
                }).collect(Collectors.toList());
    }

    public static List<UserResp> buildResp(List<User> list, Set<Long> onlineUserId) {
        return list.stream()
                .map(a -> {
                    UserResp resp = new UserResp();
                    BeanUtil.copyProperties(a, resp);
                    resp.setOnline(onlineUserId.contains(a.getUserId()) ? 1 : 0);
                    return resp;
                }).collect(Collectors.toList());
    }

    public static BaseUserResp buildResp(User user) {
        BaseUserResp resp = new BaseUserResp();
        BeanUtil.copyProperties(user, resp);
        return resp;
    }
}
