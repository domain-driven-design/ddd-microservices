package com.example.bff.application;

import com.example.bff.domain.UserContext;
import com.example.bff.utils.JwtUtil;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import utils.JacksonUtil;

@Component
@AllArgsConstructor
@Slf4j
public class AuthenticationFilter implements GlobalFilter {

    private final AuthenticationAppService authenticationAppService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            token = token.substring(7);
            UserContext userContextFromToken = JwtUtil.getUserContextFromToken(token);

            // forward token to backend service
            exchange.getRequest().mutate().header("userId", userContextFromToken.getUserId()).build();
            exchange.getRequest().mutate().header("userContext", JacksonUtil.toJson(userContextFromToken)).build();

            return chain.filter(exchange);
        } catch (SignatureException ex) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        } catch (Exception ex) {
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return exchange.getResponse().setComplete();
        }
    }
}
