CREATE TABLE `ums_user`
(
    `user_id`     bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
    `password`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码',
    `icon`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
    `email`       varchar(255)                                            DEFAULT NULL COMMENT '用户邮箱',
    `status`      tinyint                                                 DEFAULT '0' COMMENT '用户状态',
    `nickname`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
    `create_time` datetime                                                DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(255)                                            DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `uniq_name` (`username`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE,
    KEY `idx_update_time` (`update_time`) USING BTREE
);

CREATE TABLE cms_chatroom
(
    id          bigint PRIMARY KEY AUTO_INCREMENT comment '群组id',
    name        varchar(255) comment '群组姓名',
    icon        varchar(255) default null comment '群图标',
    max_number  int          default '100' comment '最大在线人数',
    manage_user bigint comment '群主',
    create_user bigint comment '创建人',
    create_time datetime     default CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    FOREIGN KEY (manage_user) REFERENCES ums_user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (create_user) REFERENCES ums_user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
) comment '群组';

CREATE TABLE map_user_chatroom
(
    id          bigint PRIMARY KEY AUTO_INCREMENT comment 'id',
    user_id     bigint comment '用户id',
    room_id     bigint comment '群聊id',
    FOREIGN KEY (user_id) REFERENCES ums_user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (room_id) REFERENCES cms_chatroom (id) ON DELETE CASCADE ON UPDATE CASCADE,
    create_time datetime default CURRENT_TIMESTAMP comment '创建时间'
) comment '用户、群聊的关联表';

CREATE TABLE cms_message
(
    id           bigint PRIMARY KEY AUTO_INCREMENT comment 'id',
    room_id      bigint comment '群聊id 群聊时',
    friendUserId bigint comment '接收用户id 私发时',
    user_id      bigint comment '用户id',
    content      varchar(1024) comment '消息内容',
    reply_msg_id bigint comment '回复的消息id',
    status       int      default 0 comment '消息状态 0正常 1删除',
    create_time  datetime default CURRENT_TIMESTAMP comment '创建时间',
    FOREIGN KEY (user_id) REFERENCES ums_user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (room_id) REFERENCES cms_chatroom (id) ON DELETE CASCADE ON UPDATE CASCADE
) comment '群聊消息表';

CREATE TABLE `ums_user_friend`
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    user_id        BIGINT COMMENT '用户id',
    friend_user_id BIGINT COMMENT '好友用户id',
    create_time    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (user_id) REFERENCES ums_user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (friend_user_id) REFERENCES ums_user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table file_config
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    file_root_path varchar(255) COMMENT '文件存储根路径',
    max_size       BIGINT COMMENT '最大存储限制 单位 B字节',
    use_size       BIGINT COMMENT '已经使用存储 单位 B字节',
    create_time    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) comment '文件配置';

insert into file_config(id, file_root_path, max_size, use_size) value (1, '/orion', 100000000, 0);

create table file_info
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    file_name   varchar(255) COMMENT '文件名',
    file_url    varchar(255) comment '文件路径',
    file_size   BIGINT comment '文件大小 单位 B字节',
    file_md5    varchar(255) comment '文件md5 用于校验文件是否存在',
    config_id   BIGINT comment '文件配置id',
    create_user BIGINT comment '创建用户',
    status      int comment '文件状态 1表示正常可用 2表示等待传输中',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (config_id) REFERENCES file_config (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (create_user) REFERENCES ums_user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
) comment '文件信息';

create table file_upload_info
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    file_info_id    BIGINT comment '文件信息id',
    file_chunk_num  int comment '总块数',
    file_chunk_size int comment '每块大小 B',
    local_url       varchar(255) comment '客户端文件地址',
    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (file_info_id) REFERENCES file_info (id) ON DELETE CASCADE ON UPDATE CASCADE
) comment '文件上传 总配置';


create table file_update
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    file_url            varchar(255) comment '文件路径',
    file_chunk          int comment '块下标',
    file_md5            varchar(255) comment '块md5',
    file_upload_info_id BIGINT comment '文件上传 id',
    create_time         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (file_upload_info_id) REFERENCES file_upload_info (id) ON DELETE CASCADE ON UPDATE CASCADE
) comment '文件上传 分片';
