package com.example.common.auth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<UserContextInitializationFilter> userContextInitializationFilter() {
        FilterRegistrationBean<UserContextInitializationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new UserContextInitializationFilter());
        registrationBean.addUrlPatterns("/*"); // Apply to all URLs

        return registrationBean;
    }
}
