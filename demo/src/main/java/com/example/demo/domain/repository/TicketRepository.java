package com.example.demo.domain.repository;

import com.example.demo.domain.aggregate.ticket.Ticket;
import org.springframework.stereotype.Repository;


public interface TicketRepository {
    Ticket get(String id);

    void add(Ticket ticket);

    long update(Ticket ticket);

    void remove(String id);
}
