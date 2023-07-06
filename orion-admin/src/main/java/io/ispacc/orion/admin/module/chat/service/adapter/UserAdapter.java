package io.ispacc.orion.admin.module.chat.service.adapter;

import cn.hutool.core.bean.BeanUtil;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.chat.controller.resp.UserResp;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 18:09
 */
public class UserAdapter {
    public static List<UserResp> buildResp(List<User> list, Set<Long> onlineUserId) {
        return list.stream()
                .map(a -> {
                    UserResp resp = new UserResp();
                    BeanUtil.copyProperties(a, resp);
                    resp.setOnline(onlineUserId.contains(a.getUserId()) ? 1 : 0);
                    return resp;
                }).collect(Collectors.toList());
    }
}
