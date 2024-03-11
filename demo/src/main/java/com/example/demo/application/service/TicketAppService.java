package com.example.demo.application.service;

import auth.AuthService;
import com.example.demo.application.assembler.TicketAssembler;
import com.example.demo.application.dto.request.CreateTicketCommand;
import com.example.demo.application.dto.request.TicketQuery;
import com.example.demo.application.dto.request.UpdateTicketCommand;
import com.example.demo.application.dto.response.TicketResponse;
import com.example.demo.domain.aggregate.ticket.Ticket;
import com.example.demo.domain.repository.TicketRepository;
import com.example.demo.infrastructure.persistence.mapper.query.TicketQueryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utils.IdUtil;
import utils.page.PageResponse;

@AllArgsConstructor
@Service
public class TicketAppService {
    public final TicketRepository ticketRepository;
    public final TicketQueryMapper ticketQueryMapper;
    public final AuthService authService;
    public final TicketAssembler ticketAssembler;

    public TicketResponse create(CreateTicketCommand createTicketCommand) {
        String currentUserId = authService.currentUserId();

        Ticket ticket = Ticket.builder()
                .id(IdUtil.uuid())
                .createdBy(currentUserId)
                .updatedBy(currentUserId)
                .title(createTicketCommand.getTitle())
                .description(createTicketCommand.getDescription()).build();
        ticketRepository.add(ticket);
        return ticketAssembler.toResponse(ticket);
    }

    public PageResponse<TicketResponse> query(TicketQuery ticketQuery) {
        return null;
    }

    public TicketResponse read(String id) {
        return null;
    }

    public void update(String id, UpdateTicketCommand updateTicketCommand) {
    }

    public void delete(String id, TicketQuery ticketQuery) {
    }
}
