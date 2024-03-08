package com.example.bff.infrastructure.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum UserIdentityRole {
    ADMIN("管理员"),
    USER("普通用户");

    private final String name;

}
