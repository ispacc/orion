package io.ispacc.orion.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


/**
 * 用户注册参数
 */
@Data
public class UserParam {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String icon;
    @Email
    private String email;
    private String nickName;
    private String remark;
}
