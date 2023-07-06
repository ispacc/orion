package io.ispacc.orion.admin.module.chat.controller.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 16:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessageReq extends MessageReq {
    @NotNull
    private Long friendUserId;
}
