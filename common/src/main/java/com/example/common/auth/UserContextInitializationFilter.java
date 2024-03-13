package com.example.common.auth;

import com.example.common.utils.JacksonUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/*")
public class UserContextInitializationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter initialization logic can be added here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            // 1. Fetch userContext json from header
            String userContextString = httpRequest.getHeader("userContext");
            if (Objects.nonNull(userContextString)) {
                // 2. parse to userContext
                UserContext userContext = JacksonUtil.fromJsonToObject(userContextString, UserContext.class);

                // 3. put it to UserContextHolder object
                UserContextHolder.setContext(userContext);
            }
        } finally {
            UserContextHolder.clear();
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
        }
        // 4. Proceed with the request
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
