package io.ispacc.orion.admin.module.admin.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.ispacc.orion.admin.module.admin.dao.UserDao;
import io.ispacc.orion.admin.module.admin.dto.UserParam;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.admin.mapper.UserMapper;
import io.ispacc.orion.admin.module.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserMapper userMapper;
    private final UserDao userDao;

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
    public SaTokenInfo login(String username, String password) {
        String token = null;
        //  TODO 密码需要客户端加密后传递
        User user = userDao.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username).eq(User::getPassword, password).eq(User::getStatus, 1));
        if (user == null) {
            return null;
        }
        StpUtil.login(user.getUserId());
        return StpUtil.getTokenInfo();
    }


}
