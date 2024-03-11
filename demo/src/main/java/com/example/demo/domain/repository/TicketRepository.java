package com.example.demo.domain.repository;

import com.example.demo.domain.aggregate.ticket.Ticket;


public interface TicketRepository {
    Ticket find(String id);

    void create(Ticket ticket);

    void update(Ticket ticket);

    void remove(String id);
}
