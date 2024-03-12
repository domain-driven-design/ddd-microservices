package com.example.demo.application.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.application.dto.response.TicketResponse;
import com.example.demo.domain.aggregate.ticket.Ticket;
import com.example.demo.infrastructure.persistence.po.TicketPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import utils.page.PageResponse;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE, uses = GeneralAssembler.class)
public interface TicketAssembler {
    TicketAssembler INSTANCE = Mappers.getMapper(TicketAssembler.class);
    TicketResponse toResponse(Ticket ticket);
    TicketResponse toResponse(TicketPO ticketPO);
    PageResponse<TicketResponse> toPageResponse(Page<TicketPO> ticketPOPage);
}
