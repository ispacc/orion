package io.ispacc.orion.admin.module.chat.controller.resp;

import io.ispacc.orion.admin.module.chat.controller.resp.user.BaseUserResp;
import lombok.Data;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-06 18:09
 */
@Data
public class RoomRemoveUserResp {
    private Long roomId;
    private BaseUserResp managerUser;
    private BaseUserResp removeUser;
}
