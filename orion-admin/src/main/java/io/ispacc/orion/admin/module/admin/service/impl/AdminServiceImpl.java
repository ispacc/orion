package io.ispacc.orion.admin.module.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.ispacc.orion.admin.module.admin.dto.UserParam;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.admin.mapper.UserMapper;
import io.ispacc.orion.admin.module.admin.service.AdminService;
import io.ispacc.orion.admin.utils.JWTUtils;
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
    public User register(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setStatus(1);
        //  查询是否有相同用户名的用户
        List<User> list = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (!list.isEmpty()) {
            return null;
        }
        //TODO 将密码进行加密操作
        user.setPassword(user.getPassword());
        userMapper.insert(user);
        return user;
    }


    @Override
    public String login(String username, String password) {
        String token = null;
//        密码需要客户端加密后传递
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username).eq(User::getPassword, password));
        if (user == null) {
            return null;
        }
        token = JWTUtils.createToken(user);
        return token;
    }


}
