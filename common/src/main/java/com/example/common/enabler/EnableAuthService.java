package com.example.common.enabler;

import com.example.common.auth.AuthService;
import com.example.common.auth.FilterConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        // Helper service for UserContext fetching
        AuthService.class,
        // Filter for UserContext creation
        FilterConfig.class})
public @interface EnableAuthService {
}
