package com.example.common.lock.mysql.repository;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
@Builder
@TableName("distribute_lock")
public class MysqlLockPO {

    @TableField("id")
    private String id;

    @TableField("lock_key")
    private String lockKey;
    // 过期时间
    @TableField("expiration_time")
    private OffsetDateTime expirationTime;
}
