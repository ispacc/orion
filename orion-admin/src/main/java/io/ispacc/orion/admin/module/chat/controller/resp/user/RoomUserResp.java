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
public class RoomUserResp extends UserResp {
    //是否是管理员 1是 0不是
    private Integer manager;
}
