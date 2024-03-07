package com.example.bff.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class GatewayConfig {

    @Bean
    public GlobalFilter customFilter() {
        return (exchange, chain) -> {
            System.out.println("Global Pre Filter: " + exchange.getRequest().getPath());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Global Post Filter: " + exchange.getResponse().getStatusCode());
            }));
        };
    }
}
