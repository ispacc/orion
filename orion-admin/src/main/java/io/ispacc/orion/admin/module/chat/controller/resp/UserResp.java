package io.ispacc.orion.admin.module.chat.controller.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 16:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResp implements Serializable {
    //用户ID
    private Long userId;
    //用户名
    private String username;
    //用户头像
    private String icon;
    //用户邮箱
    private String email;
    //用户状态
    private Integer status;
    //用户昵称
    private String nickname;

    //是否在线 1在线 0离线
    private Integer online;
}
