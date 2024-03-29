package io.ispacc.orion.chat.module.chat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.ispacc.orion.chat.module.admin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户 群聊 关联表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:14
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("map_user_chatroom")
public class UserRoom extends Model<User> {
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 群组id
     */
    private Long roomId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
