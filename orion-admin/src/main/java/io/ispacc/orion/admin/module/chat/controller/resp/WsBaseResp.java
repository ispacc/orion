package io.ispacc.orion.admin.module.chat.controller.resp;

import lombok.Data;

/**
 * ws消息基本类型体
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 17:31
 */
@Data
public class WsBaseResp<T> {
    private Integer type;
    private T data;
}
