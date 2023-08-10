package io.ispacc.orion.chat.module.admin.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import io.ispacc.orion.chat.module.admin.dto.UserParam;
import io.ispacc.orion.chat.module.admin.entity.User;
import io.ispacc.orion.chat.module.admin.service.AdminService;
import io.ispacc.orion.common.api.CommonResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    /**
     * Registers a new user with the given user parameters.
     *
     * @param userParam the user parameters containing the necessary details for registration
     * @return the result of the registration process. Returns a {@link CommonResult} object with the registered user if successful,
     * or a failed result if the registration process fails
     */
    @PostMapping("/register")
    @SaIgnore
    public CommonResult<User> register(@Valid @RequestBody UserParam userParam) {
        User user = adminService.register(userParam);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }

    /**
     * Authenticates the user with the specified username and password.
     *
     * @param userParam the user parameters containing the username and password of the user to authenticate
     * @return the result of the login process. Returns a {@link CommonResult} object with a {@link SaTokenInfo} object representing
     * the authentication result if successful, or a failed result with an error message if the login process fails
     */
    @PostMapping("/login")
    @SaIgnore
    public CommonResult<SaTokenInfo> login(@Valid @RequestBody UserParam userParam) {
        SaTokenInfo token = adminService.login(userParam.getUsername(), userParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        return CommonResult.success(token);
    }
}
