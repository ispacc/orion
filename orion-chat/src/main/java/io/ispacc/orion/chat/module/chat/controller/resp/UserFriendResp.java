package io.ispacc.orion.chat.module.chat.controller.resp;

import io.ispacc.orion.chat.module.chat.controller.resp.user.UserResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:34
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFriendResp implements Serializable {
    private Long id;
    private Long userId;
    private Long friendUserId;
    private UserResp friendUser;
}
