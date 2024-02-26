create table user_identity
(
    id                   varchar(64)                        not null comment '用户身份ID'
        primary key,
    user_id              varchar(64)                        not null comment '用户ID',
    permission_branch_id varchar(64)                        null comment '权限机构ID',
    created_by           varchar(64)                        not null comment '创建人',
    created_time         datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_by           varchar(64)                        not null comment '更新人',
    updated_time         datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户身份';
