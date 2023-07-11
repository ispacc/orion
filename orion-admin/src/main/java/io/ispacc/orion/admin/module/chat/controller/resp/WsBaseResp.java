package io.ispacc.orion.admin.module.chat.controller.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ws消息基本类型体
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 17:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WsBaseResp<T> {
    private Integer type;
    private T data;
}
