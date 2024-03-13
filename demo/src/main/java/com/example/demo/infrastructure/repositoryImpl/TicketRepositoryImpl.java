package com.example.demo.infrastructure.repositoryImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.domain.aggregate.ticket.Ticket;
import com.example.demo.domain.aggregate.ticket.TicketTask;
import com.example.demo.domain.exception.DemoErrorCode;
import com.example.demo.domain.repository.TicketRepository;
import com.example.demo.infrastructure.converter.TicketConverter;
import com.example.demo.infrastructure.converter.TicketTaskConverter;
import com.example.demo.infrastructure.persistence.mapper.base.TicketMapper;
import com.example.demo.infrastructure.persistence.mapper.base.TicketTaskMapper;
import com.example.demo.infrastructure.persistence.po.TicketPO;
import com.example.common.error.BusinessException;
import com.example.common.error.SystemException;
import com.example.demo.infrastructure.persistence.po.TicketTaskPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {
    public final TicketMapper ticketMapper;
    public final TicketTaskMapper ticketTaskMapper;
    public final TicketConverter ticketConverter;
    public final TicketTaskConverter ticketTaskConverter;

    @Override
    public void create(Ticket ticket) {
        TicketPO ticketPO = ticketConverter.toPO(ticket);
        int result = ticketMapper.insert(ticketPO);
        if (result != 1) {
            throw new SystemException(DemoErrorCode.CREATE_TICKET_FAILED);
        }
    }

    @Override
    public Ticket find(String id) {
        TicketPO ticketPO = Optional.ofNullable(ticketMapper.selectById(id)).orElseThrow(() -> {
            throw new BusinessException(DemoErrorCode.TICKET_NOT_FOUND);
        });
        // Hints: Dealing aggregation in repository
        Ticket ticket = ticketConverter.toEntity(ticketPO);
        List<TicketTask> ticketTasks = getTicketTasks(id);
        ticket.updateTasks(ticketTasks);
        return ticket;
    }

    private List<TicketTask> getTicketTasks(String id) {
        LambdaQueryWrapper<TicketTaskPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TicketTaskPO::getTicketId, id);
        return ticketTaskMapper.selectList(wrapper).stream().map(ticketTaskConverter::toEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(Ticket ticket) {
        // Hints: Dealing aggregation in repository
        // 1. update aggregate root
        TicketPO ticketPO = ticketConverter.toPO(ticket);
        int result = ticketMapper.updateById(ticketPO);
        if (result != 1) {
            throw new BusinessException(DemoErrorCode.UPDATE_TICKET_FAILED);
        }
        // 2. Remove association objects
        LambdaQueryWrapper<TicketTaskPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TicketTaskPO::getTicketId, ticket.getId());
        ticketTaskMapper.delete(wrapper);
        // 3. Insert association objects
        ticket.getTasks().forEach(ticketTask -> {
            ticketTaskMapper.insert(ticketTaskMapper.toPO(ticketTask));
        });
    }

    @Override
    public void remove(String id) {
        // Idempotence
        ticketMapper.deleteById(id);
    }
}
