package com.example.demo.infrastructure.converter;

import com.example.demo.domain.aggregate.ticket.TicketTask;
import com.example.demo.infrastructure.persistence.po.TicketTaskPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
public interface TicketTaskConverter {
    TicketTaskConverter INSTANCE = Mappers.getMapper(TicketTaskConverter.class);

    TicketTaskPO toPO(TicketTask ticketTask);
    TicketTask toEntity(TicketTaskPO ticketTaskPO);
}
