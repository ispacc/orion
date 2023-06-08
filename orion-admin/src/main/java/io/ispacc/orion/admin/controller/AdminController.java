package io.ispacc.orion.admin.controller;

import io.ispacc.orion.admin.common.CommonResult;
import io.ispacc.orion.admin.dto.UserParam;
import io.ispacc.orion.admin.entity.User;
import io.ispacc.orion.admin.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AdminService adminService;

    @Operation(description = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<User> register(@Validated @RequestBody UserParam userParam) {
        User user = adminService.register(userParam);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(user);
    }


    @PostMapping()
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
