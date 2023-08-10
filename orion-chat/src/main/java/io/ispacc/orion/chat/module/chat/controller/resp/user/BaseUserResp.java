package io.ispacc.orion.chat.module.chat.controller.resp.user;

import lombok.Data;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-06 18:34
 */
@Data
public class BaseUserResp {
    private Long userId;
    //用户名
    private String username;
    //用户头像
    private String icon;
    //用户邮箱
    private String email;
    //用户昵称
    private String nickname;
}
