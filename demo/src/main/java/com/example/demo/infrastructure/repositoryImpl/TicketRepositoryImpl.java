package com.example.demo.infrastructure.repositoryImpl;

import com.example.demo.domain.aggregate.ticket.Ticket;
import com.example.demo.domain.repository.TicketRepository;
import com.example.demo.infrastructure.persistence.mapper.base.TicketMapper;
import com.example.demo.infrastructure.persistence.mapper.base.TicketTaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {
    public final TicketMapper ticketMapper;
    public final TicketTaskMapper ticketTaskMapper;

    @Override
    public Ticket get(String id) {
        return null;
    }

    @Override
    public Ticket add(Ticket ticket) {
        return null;
    }

    @Override
    public long update(Ticket ticket) {
        return 0;
    }

    @Override
    public void remove(String id) {

    }
}
