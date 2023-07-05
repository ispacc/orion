package io.ispacc.orion.admin.module.admin.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.admin.module.admin.entity.UserFriend;
import io.ispacc.orion.admin.module.admin.mapper.UserFriendMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 16:21
 */
@AllArgsConstructor
@Service
public class UserFriendDao extends ServiceImpl<UserFriendMapper, UserFriend> {
    private final UserFriendMapper userFriendMapper;

    public List<Long> getUserFriendIdsByUserId(Long userId) {
        List<UserFriend> userFriends = lambdaQuery().eq(UserFriend::getUserId, userId).list();
        return userFriends.stream().map(UserFriend::getFriendUserId).collect(Collectors.toList());
    }

    public Long countByUserIdAndUserFriendId(Long userId, Long userFriendId) {
        return lambdaQuery().eq(UserFriend::getUserId, userId).eq(UserFriend::getFriendUserId, userFriendId).count();
    }
}
