package io.ispacc.orion.admin.module.chat.controller.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private LocalDateTime createTime;
}
