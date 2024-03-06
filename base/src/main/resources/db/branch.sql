create table branch
(
    id           varchar(64) collate utf8mb4_bin       not null comment '机构 ID (编码)'
        primary key,
    parent_id    varchar(64) collate utf8mb4_bin       null comment '父级机构编码',
    name         varchar(64) collate utf8mb4_bin       not null comment '机构名称',
    l1_branch_id varchar(64)                           null comment '一级机构编码',
    parent_name  varchar(64)                           null comment '父级机构名称',
    path_name    varchar(128)                          not null comment '机构路径名',
    level        varchar(12) collate utf8mb4_bin       null comment '机构层级',
    created_by   varchar(64) collate utf8mb4_bin       not null comment '创建人',
    created_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_by   varchar(64) collate utf8mb4_bin       not null comment '更新人',
    updated_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '核心机构' charset = utf8mb4;
