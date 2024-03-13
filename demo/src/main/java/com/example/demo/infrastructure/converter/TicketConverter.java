package com.example.demo.infrastructure.converter;

import com.example.demo.domain.aggregate.ticket.Ticket;
import com.example.demo.infrastructure.persistence.po.TicketPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
public interface TicketConverter {
    TicketConverter INSTANCE = Mappers.getMapper(TicketConverter.class);

    TicketPO toPO(Ticket ticket);
    Ticket toEntity(TicketPO ticketPO);
}
