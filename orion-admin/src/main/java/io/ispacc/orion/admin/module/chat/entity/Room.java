package io.ispacc.orion.admin.module.chat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.ispacc.orion.admin.module.admin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 群聊表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_room")
public class Room extends Model<User> {
    @TableId
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
