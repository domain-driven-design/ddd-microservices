package com.example.demo.application.service;

import com.example.demo.application.dto.request.CreateTicketCommand;
import com.example.demo.application.dto.request.TicketQuery;
import com.example.demo.application.dto.request.UpdateTicketCommand;
import com.example.demo.application.dto.response.TicketResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utils.page.PageResponse;

@AllArgsConstructor
@Service
public class TicketAppService {
    public TicketResponse createTicket(CreateTicketCommand command) {
        return null;
    }

    public TicketResponse create(CreateTicketCommand createTicketCommand) {
        return null;
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
