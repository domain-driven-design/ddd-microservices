package com.example.demo.infrastructure.convert;

import com.example.demo.application.assembler.GeneralAssembler;
import com.example.demo.domain.aggregate.ticket.Ticket;
import com.example.demo.infrastructure.persistence.po.TicketPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE, uses = GeneralAssembler.class)
public interface TicketConvert {
    TicketConvert INSTANCE = Mappers.getMapper(TicketConvert.class);

    TicketPO toPO(Ticket ticket);
    Ticket toEntity(TicketPO ticketPO);
}
