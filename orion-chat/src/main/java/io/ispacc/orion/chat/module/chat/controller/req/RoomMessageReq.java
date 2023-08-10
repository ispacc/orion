package io.ispacc.orion.chat.module.chat.controller.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 16:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomMessageReq extends MessageReq {
    @NotNull
    @Min(1)
    private Long roomId;

}
