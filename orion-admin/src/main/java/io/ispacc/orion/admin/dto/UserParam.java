package io.ispacc.orion.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


/**
 * 用户注册参数
 */
@Data
public class UserParam {
    @NotEmpty
    @Schema(description = "用户名", required = true)
    private String username;
    @NotEmpty
    @Schema(description = "密码", required = true)
    private String password;
    @Schema(description = "用户头像")
    private String icon;
    @Email
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "用户昵称")
    private String nickName;
    @Schema(description = "备注")
    private String remark;
}
