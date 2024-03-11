package com.example.demo.infrastructure.persistence.mapper.query;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.aggregate.ticket.Ticket;
import com.example.demo.infrastructure.persistence.po.TicketPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketQueryMapper extends BaseMapper<TicketPO> {

}
