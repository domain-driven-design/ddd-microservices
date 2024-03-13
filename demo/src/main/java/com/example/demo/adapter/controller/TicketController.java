package com.example.demo.adapter.controller;

import com.example.demo.application.dto.request.CreateTicketCommand;
import com.example.demo.application.dto.request.TicketQuery;
import com.example.demo.application.dto.request.UpdateTicketCommand;
import com.example.demo.application.dto.response.TicketResponse;
import com.example.demo.application.service.TicketAppService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.common.utils.page.PageResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/tickets")
@AllArgsConstructor
public class TicketController {
    @Autowired
    private final TicketAppService ticketAppService;

    @PostMapping
    public ResponseEntity<TicketResponse> create(@RequestBody @Valid CreateTicketCommand createTicketCommand) {
        return ResponseEntity.ok(ticketAppService.create(createTicketCommand));
    }

    @GetMapping
    public ResponseEntity<PageResponse<TicketResponse>> query(TicketQuery ticketQuery) {
        return ResponseEntity.ok(ticketAppService.query(ticketQuery));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> read(@PathVariable String id) {
        return ResponseEntity.ok(ticketAppService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody @Valid UpdateTicketCommand updateTicketCommand) {
        ticketAppService.update(id, updateTicketCommand);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        ticketAppService.delete(id);
        return ResponseEntity.ok().build();
    }
}
