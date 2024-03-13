package com.example.common.lock.mysql.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MysqlLockMapper extends BaseMapper<MysqlLockPO> {
}
