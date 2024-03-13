package com.example.demo.application.service;

import com.example.common.auth.AuthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.application.assembler.TicketAssembler;
import com.example.demo.application.dto.request.CreateTicketCommand;
import com.example.demo.application.dto.request.TicketQuery;
import com.example.demo.application.dto.request.UpdateTicketCommand;
import com.example.demo.application.dto.response.TicketResponse;
import com.example.demo.domain.aggregate.ticket.Ticket;
import com.example.demo.domain.repository.TicketRepository;
import com.example.demo.infrastructure.persistence.mapper.query.TicketQueryMapper;
import com.example.demo.infrastructure.persistence.po.TicketPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.common.utils.page.PageResponse;

import java.util.Objects;

@AllArgsConstructor
@Service
public class TicketAppService {
    public final TicketRepository ticketRepository;
    public final TicketQueryMapper ticketQueryMapper;
    public final AuthService authService;
    public final TicketAssembler ticketAssembler;

    public TicketResponse create(CreateTicketCommand createTicketCommand) {
        String currentUserId = authService.currentUserId();
        Ticket ticket = createTicketCommand.toEntity(currentUserId);
        ticketRepository.create(ticket);
        return ticketAssembler.toResponse(ticket);
    }

    public PageResponse<TicketResponse> query(TicketQuery ticketQuery) {
        LambdaQueryWrapper<TicketPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Objects.nonNull(ticketQuery.getTitle()), TicketPO::getTitle, ticketQuery.getTitle());
        Page<TicketPO> page = new Page<>(ticketQuery.getPageNumber(), ticketQuery.getPageSize());

        Page<TicketPO> poPage = ticketQueryMapper.selectPage(page, queryWrapper);
        return ticketAssembler.toPageResponse(poPage);
    }

    public TicketResponse read(String id) {
        Ticket ticket = ticketRepository.find(id);
        return ticketAssembler.toResponse(ticket);
    }

    public void update(String id, UpdateTicketCommand updateTicketCommand) {
        String currentUserId = authService.currentUserId();
        Ticket ticket = ticketRepository.find(id);
        ticket.update(currentUserId, updateTicketCommand.getTitle(), updateTicketCommand.getDescription());
        ticketRepository.update(ticket);
    }

    public void delete(String id) {
        ticketRepository.remove(id);
    }
}
