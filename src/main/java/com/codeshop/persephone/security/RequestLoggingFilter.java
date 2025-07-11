package com.codeshop.persephone.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (uri.contains("/actuator")) {
            filterChain.doFilter(request, response);
            return;
        }

        String method = request.getMethod();
        String ip = request.getRemoteAddr();
        String referer = request.getHeader("Referer");
        String body = request.getReader().lines().reduce("", (a, b) -> a + b);

        if (StringUtils.isNotBlank(body)) {
            log.info("Incoming request: {} {} (Referer: {}, IP: {})\n{}", method, uri, referer, ip, body);
        } else {
            log.info("Incoming request: {} {} (Referer: {}, IP: {})", method, uri, referer, ip);
        }

        filterChain.doFilter(request, response);
    }
}
