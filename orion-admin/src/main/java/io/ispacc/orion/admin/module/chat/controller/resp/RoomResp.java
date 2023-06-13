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
public class RoomResp implements Serializable {
    private Long id;

    /**
     * 用户id
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

    /**
     * 创建时间
     */
    private LocalDateTime timeCreate;

    /**
     * 更新时间
     */
    private LocalDateTime timeUpdate;
}
