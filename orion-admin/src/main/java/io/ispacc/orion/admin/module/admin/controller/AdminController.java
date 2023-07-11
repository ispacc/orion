package io.ispacc.orion.admin.module.admin.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import io.ispacc.orion.admin.core.common.CommonResult;
import io.ispacc.orion.admin.module.admin.dto.UserParam;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.admin.service.AdminService;
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

    @PostMapping("/register")
    @SaIgnore
    public CommonResult<User> register(@Valid @RequestBody UserParam userParam) {
        User user = adminService.register(userParam);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }


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
