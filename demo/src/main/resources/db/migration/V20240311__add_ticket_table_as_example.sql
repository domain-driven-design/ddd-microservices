CREATE TABLE ticket
(
    id           VARCHAR(64)                        NOT NULL COMMENT '工单 ID',
    title        VARCHAR(128)                       NOT NULL COMMENT '工单标题',
    description  TEXT                               NOT NULL COMMENT '工单描述',
    creator_id   VARCHAR(64)                        NOT NULL COMMENT '创建者 ID',
    assignee_id  VARCHAR(64) NULL COMMENT '指派者 ID',
    status       ENUM('TODO', 'DOING', 'DONE') NOT NULL COMMENT '工单状态',
    created_by   varchar(64) collate utf8mb4_bin    not null comment '创建人',
    created_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_by   varchar(64) collate utf8mb4_bin    not null comment '更新人',
    updated_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
        PRIMARY KEY (id)
) COMMENT '工单信息表' CHARSET = utf8mb4;

CREATE TABLE ticket_task
(
    id           VARCHAR(64)                        NOT NULL COMMENT '任务 ID',
    ticket_id    VARCHAR(64)                        NOT NULL COMMENT '工单 ID',
    name         VARCHAR(128)                       NOT NULL COMMENT '任务名称',
    description  TEXT NULL COMMENT '任务描述',
    assignee_id  VARCHAR(64)                        NOT NULL COMMENT '指派者 ID',
    status       ENUM('TODO', 'DOING', 'DONE') NOT NULL COMMENT '任务状态 ID'
        priority ENUM('LOW', 'MEDIUM', 'HIGH') NOT NULL COMMENT '任务优先级',
    created_by   varchar(64) collate utf8mb4_bin    not null comment '创建人',
    created_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_by   varchar(64) collate utf8mb4_bin    not null comment '更新人',
    updated_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    due_time     DATETIME NULL COMMENT '截止时间',
    PRIMARY KEY (id)
) COMMENT '工单任务表' CHARSET = utf8mb4;
