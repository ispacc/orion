package io.ispacc.orion.admin.service;

import io.ispacc.orion.admin.dto.UserParam;
import io.ispacc.orion.admin.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {


    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);


    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 注册功能
     */
    User register(UserParam userParam);
}
