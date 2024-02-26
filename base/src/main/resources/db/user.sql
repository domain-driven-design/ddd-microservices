create table user
(
    id                  varchar(64)                          not null comment '用户ID（员工编号）'
        primary key,
    name                varchar(64)                          not null comment '用户名',
    current_identity_id varchar(64)                          not null comment '当前的身份ID',
    abnormal_batch      varchar(8)                           null comment '异常批次',
    status              varchar(12)                          not null comment '状态',
    deleted             tinyint(1) default 0                 not null comment '是否删除',
    maintain_by         varchar(64)                          not null comment '维护人ID',
    maintain_by_name    varchar(64)                          not null comment '维护人姓名',
    maintain_time       datetime                             not null comment '维护时间',
    version             int        default 1                 not null comment '版本',
    created_by          varchar(64)                          not null comment '创建人',
    created_time        datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_by          varchar(64)                          not null comment '更新人',
    updated_time        datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户';
