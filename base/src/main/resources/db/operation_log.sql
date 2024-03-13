create table operation_log
(
    id                  VARCHAR(64)                           not null comment 'id' PRIMARY KEY,
    operation_type       VARCHAR(10)                          not null comment '操作类型',
    operation_scene      VARCHAR(64)                          not null comment '操作场景',
    operation_result     VARCHAR(10)                          not null comment '操作结果',
    operator_ip          VARCHAR(64)                          not null comment '操作人ip',
    operator_id          VARCHAR(64)                          not null comment '操作人id',
    operator_name        VARCHAR(64)                          not null comment '操作人',
    additional_info      TEXT                                 null comment '其他信息',
    created_by           VARCHAR(64)                          not null comment '创建人',
    created_time         datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_by           VARCHAR(64)                          not null comment '更新人',
    updated_time         datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '操作日志' charset = utf8mb4;
