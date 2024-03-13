package com.example.demo.infrastructure.persistence.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.aggregate.ticket.TicketTask;
import com.example.demo.infrastructure.persistence.po.TicketTaskPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketTaskMapper extends BaseMapper<TicketTaskPO> {
    TicketTaskPO toPO(TicketTask ticketTask);
    TicketTask toEntity(TicketTaskPO ticketTaskPO);
}
