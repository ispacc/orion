package io.ispacc.orion.admin.module.chat.controller.resp;

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
public class RoomResp implements Serializable {
    private Long id;

    /**
     * room名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 最大人数
     */
    private Integer maxNumber;
}
