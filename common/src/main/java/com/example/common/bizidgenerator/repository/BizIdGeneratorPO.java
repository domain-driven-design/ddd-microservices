package com.example.common.bizidgenerator.repository;

import com.example.common.bizidgenerator.domain.BizType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@TableName("biz_id_generator")
public class BizIdGeneratorPO {

    // 业务类型，id
    @TableField("id")
    private BizType bizType;
    // 序号
    private Integer seq;
    // 过期时间
    private OffsetDateTime expirationTime;
    // 过期天数
    private Integer expireDays;

}
