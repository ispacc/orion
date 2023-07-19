package io.ispacc.orion.chat.module.chat.controller.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 16:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessageReq extends MessageReq {
    @NotNull
    private Long friendUserId;
}
