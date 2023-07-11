package io.ispacc.orion.admin.module.admin.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.ispacc.orion.admin.module.admin.dto.UserParam;
import io.ispacc.orion.admin.module.admin.entity.User;

public interface AdminService {


    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    SaTokenInfo login(String username, String password);

    /**
     * 注册功能
     */
    User register(UserParam userParam);
}
