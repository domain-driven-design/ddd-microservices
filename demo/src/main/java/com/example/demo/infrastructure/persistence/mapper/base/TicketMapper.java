package com.example.demo.infrastructure.persistence.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.infrastructure.persistence.po.TicketPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper extends BaseMapper<TicketPO> {
    // MyBatis-Plus provides CRUD methods, add custom methods if needed
}
