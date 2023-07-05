package io.ispacc.orion.admin.module.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 20:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_user_friend")
public class UserFriend extends Model<User> {
    private Long id;
    private Long userId;
    private Long friendUserId;
    private LocalDateTime createTime;
}
