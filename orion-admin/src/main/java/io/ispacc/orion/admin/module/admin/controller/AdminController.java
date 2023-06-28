package io.ispacc.orion.admin.module.admin.controller;

import io.ispacc.orion.admin.core.common.CommonResult;
import io.ispacc.orion.admin.module.admin.dto.UserParam;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    @Value("${orion.jwt.tokenHead}")
    private String tokenHead;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public CommonResult<User> register(@Validated @RequestBody UserParam userParam) {
        User user = adminService.register(userParam);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }


    @PostMapping("/login")
    public CommonResult login(@Validated @RequestBody UserParam userParam) {
        String token = adminService.login(userParam.getUsername(), userParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
