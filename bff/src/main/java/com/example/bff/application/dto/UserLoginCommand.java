package com.example.bff.application.dto;

import lombok.Data;


@Data
public class UserLoginCommand {
    private String username;
    private String password;
}
