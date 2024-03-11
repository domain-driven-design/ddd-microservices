package com.example.demo.domain.repository;

import com.example.demo.domain.aggregate.ticket.Ticket;
import org.springframework.stereotype.Repository;


public interface TicketRepository {
    Ticket get(String id);

    Ticket add(Ticket ticket);

    long update(Ticket ticket);

    void remove(String id);
}
