package io.ispacc.orion.admin.module.chat.controller.resp.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResp extends BaseUserResp {
    //是否在线 1在线 0离线
    private Integer online;
}
