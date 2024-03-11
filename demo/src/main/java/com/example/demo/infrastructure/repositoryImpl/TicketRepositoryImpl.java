package com.example.demo.infrastructure.repositoryImpl;

import com.example.demo.domain.aggregate.ticket.Ticket;
import com.example.demo.domain.exception.DemoErrorCode;
import com.example.demo.domain.repository.TicketRepository;
import com.example.demo.infrastructure.convert.TicketConvert;
import com.example.demo.infrastructure.persistence.mapper.base.TicketMapper;
import com.example.demo.infrastructure.persistence.mapper.base.TicketTaskMapper;
import com.example.demo.infrastructure.persistence.po.TicketPO;
import error.BusinessException;
import error.SystemException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {
    public final TicketMapper ticketMapper;
    public final TicketTaskMapper ticketTaskMapper;
    public final TicketConvert ticketConvert;

    @Override
    public void create(Ticket ticket) {
        TicketPO ticketPO = ticketConvert.toPO(ticket);
        int result = ticketMapper.insert(ticketPO);
        if (result != 1) {
            throw new SystemException(DemoErrorCode.DEMO001);
        }
    }

    @Override
    public Ticket find(String id) {
        TicketPO ticketPO = Optional.ofNullable(ticketMapper.selectById(id)).orElseThrow(() -> {
            throw new BusinessException(DemoErrorCode.DEMO002);
        });
        // TODO Assembling tasks
        return ticketConvert.toEntity(ticketPO);
    }

    @Override
    public void update(Ticket ticket) {
        TicketPO ticketPO = ticketConvert.toPO(ticket);
        int result = ticketMapper.updateById(ticketPO);
        if (result != 1) {
            throw new BusinessException(DemoErrorCode.DEMO003);
        }
    }

    @Override
    public void remove(String id) {
        // Idempotence
        ticketMapper.deleteById(id);
    }
}
