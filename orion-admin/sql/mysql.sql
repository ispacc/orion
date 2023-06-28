CREATE TABLE `t_user`
(
    `user_id`     bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
    `password`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码',
    `icon`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
    `email`       varchar(255)                                            DEFAULT NULL COMMENT '用户邮箱',
    `status`      tinyint                                                 DEFAULT '0' COMMENT '用户状态',
    `nickname`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
    `time_create` datetime                                                DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `time_update` datetime                                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(255)                                            DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `uniq_name` (`username`) USING BTREE,
    KEY `idx_create_time` (`time_create`) USING BTREE,
    KEY `idx_update_time` (`time_update`) USING BTREE
);

CREATE TABLE t_room
(
    id          bigint PRIMARY KEY AUTO_INCREMENT comment '群组id',
    name        varchar(255) comment '群组姓名',
    icon        varchar(255) default null comment '群图标',
    max_number  int          default '100' comment '最大在线人数',
    time_create datetime     default CURRENT_TIMESTAMP comment '创建时间',
    time_update datetime     default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) comment '群组';

CREATE TABLE t_user_room
(
    id          bigint PRIMARY KEY AUTO_INCREMENT comment 'id',
    user_id     bigint comment '用户id',
    room_id     bigint comment '群聊id',
    FOREIGN KEY (user_id) REFERENCES t_user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (room_id) REFERENCES t_room (id) ON DELETE CASCADE ON UPDATE CASCADE,
    time_create datetime default CURRENT_TIMESTAMP comment '创建时间'
) comment '用户、群聊的关联表';

CREATE TABLE t_message
(
    id           bigint PRIMARY KEY AUTO_INCREMENT comment 'id',
    room_id      bigint comment '群聊id',
    user_id      bigint comment '用户id',
    content      varchar(1024) comment '消息内容',
    reply_msg_id bigint comment '回复的消息id',
    status       int      default 0 comment '消息状态 0正常 1删除',
    time_create  datetime default CURRENT_TIMESTAMP comment '创建时间',
    FOREIGN KEY (user_id) REFERENCES t_user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (room_id) REFERENCES t_room (id) ON DELETE CASCADE ON UPDATE CASCADE
) comment '群聊消息表';