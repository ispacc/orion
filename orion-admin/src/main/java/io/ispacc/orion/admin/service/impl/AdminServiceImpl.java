package io.ispacc.orion.admin.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.ispacc.orion.admin.dto.UserParam;
import io.ispacc.orion.admin.entity.User;
import io.ispacc.orion.admin.mapper.UserMapper;
import io.ispacc.orion.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {


    private final UserMapper userMapper;

    public AdminServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
//        密码需要客户端加密后传递

        return token;
    }

    @Override
    public User register(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setStatus(1);
        //  查询是否有相同用户名的用户
        List<User> list = new LambdaQueryChainWrapper<>(userMapper).eq(User::getUsername, user.getUsername()).list();
        if (list.size() > 0) {
            return null;
        }
        //TODO 将密码进行加密操作
        user.setPassword(user.getPassword());
        userMapper.insert(user);
        return user;
    }
}
