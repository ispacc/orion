package io.ispacc.orion.admin.module.chat.controller.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 16:03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomMessageReq extends MessageReq {
    @NotNull
    @Min(1)
    private Long roomId;

}
