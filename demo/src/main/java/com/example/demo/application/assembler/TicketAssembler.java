package com.example.demo.application.assembler;

import com.example.demo.application.dto.response.TicketResponse;
import com.example.demo.domain.aggregate.ticket.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE, uses = GeneralAssembler.class)
public interface TicketAssembler {
    TicketAssembler INSTANCE = Mappers.getMapper(TicketAssembler.class);

    TicketResponse toResponse(Ticket ticket);
}
