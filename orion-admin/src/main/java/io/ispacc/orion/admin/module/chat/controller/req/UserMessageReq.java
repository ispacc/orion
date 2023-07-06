package io.ispacc.orion.admin.module.chat.controller.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 16:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserMessageReq extends MessageReq {
    @NotNull
    private Long friendUserId;
}
