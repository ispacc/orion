package io.ispacc.orion.admin.module.admin.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.ispacc.orion.admin.module.admin.dto.UserParam;
import io.ispacc.orion.admin.module.admin.entity.User;

public interface AdminService {


    /**
     * Authenticates the user with the specified username and password.
     *
     * @param username the username of the user to authenticate
     * @param password the password of the user to authenticate
     * @return a SaTokenInfo object representing the authentication result
     */
    SaTokenInfo login(String username, String password);

    /**
     * Registers a new user with the specified user parameter.
     *
     * @param userParam the user parameter containing the information of the user to register
     * @return a User object representing the registered user
     */
    User register(UserParam userParam);
}
