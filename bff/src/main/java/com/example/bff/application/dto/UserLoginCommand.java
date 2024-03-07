package com.example.bff.application.dto;

import lombok.Data;


@Data
public class UserLoginCommand {
    private String userName;
    private String password;
}
