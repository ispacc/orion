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
 * 消息表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-15 17:31
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cms_message")
public class Message extends Model<User> {
    @TableId
    private Long id;

    private Long roomId;

    private Long friendUserId;

    private Long userId;

    private String content;

    private Long replyMsgId;

    private Integer status;

    private LocalDateTime createTime;
}
