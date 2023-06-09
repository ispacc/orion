package io.ispacc.orion.admin.service;

import io.ispacc.orion.admin.dto.UserParam;
import io.ispacc.orion.admin.entity.User;

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
     * 注册功能
     */
    User register(UserParam userParam);
}
