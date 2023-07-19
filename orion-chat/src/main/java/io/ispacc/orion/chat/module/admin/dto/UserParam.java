package io.ispacc.orion.chat.module.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * 用户注册参数
 */
@Data
public class UserParam {
    @NotBlank(message = "请输入用户名")
    private String username;
    @NotBlank(message = "请输入密码")
    private String password;
    private String icon;
    @Email(message = "邮箱格式不正确")
    private String email;
    private String nickName;
    private String remark;
}
