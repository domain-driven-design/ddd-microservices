create table operation_log
(
    id                  VARCHAR(64)                          not null comment 'id' PRIMARY KEY,
    operationType       VARCHAR(10)                          not null comment '操作类型',
    operationScene      VARCHAR(64)                          not null comment '操作场景',
    operationResult     VARCHAR(10)                          not null comment '操作结果',
    operatorIp          VARCHAR(64)                          not null comment '操作人ip',
    operatorId          VARCHAR(64)                          not null comment '操作人id',
    operatorName        VARCHAR(64)                          not null comment '操作人',
    additionalInfo      TEXT                                 not null comment '其他信息',
    createdBy           VARCHAR(64)                          not null comment '创建人',
    createdTime         datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updatedBy           VARCHAR(64)                          not null comment '更新人',
    updatedTime         datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
) comment '操作日志';
