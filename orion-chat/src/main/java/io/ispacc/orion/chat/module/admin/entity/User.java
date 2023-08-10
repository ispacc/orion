package io.ispacc.orion.chat.module.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * (User)表实体类
 *
 * @author ispacc
 * @since 2023-06-08 14:44:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_user")
public class User extends Model<User> {
    //用户ID
    @TableId
    private Long userId;
    //用户名
    private String username;
    //用户密码
    private String password;
    //用户头像
    private String icon;
    //用户邮箱
    private String email;
    //用户状态
    private Integer status;
    //用户昵称
    private String nickname;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //备注
    private String remark;
}

