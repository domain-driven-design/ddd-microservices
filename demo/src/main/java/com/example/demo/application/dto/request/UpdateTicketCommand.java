package com.example.demo.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketCommand {
    @NotNull
    private String title;
    @NotNull
    private String description;
}
