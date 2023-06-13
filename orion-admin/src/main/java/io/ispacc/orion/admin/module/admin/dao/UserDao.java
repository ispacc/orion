package io.ispacc.orion.admin.module.admin.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.admin.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 16:21
 */
@AllArgsConstructor
@Service
public class UserDao extends ServiceImpl<UserMapper, User> {
    private final UserMapper userMapper;

    public List<User> getByRoomId(Long roomId) {
        return userMapper.getUsersByRoomId(roomId);
    }
}
