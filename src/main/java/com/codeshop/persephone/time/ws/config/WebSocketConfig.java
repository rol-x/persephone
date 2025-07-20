package com.codeshop.persephone.time.ws.config;

import com.codeshop.persephone.time.ws.DateTimeWebSocketHandler;
import com.codeshop.persephone.time.ws.UnixTimeWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@EnableScheduling
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;

    private final UnixTimeWebSocketHandler unixTimeWebSocketHandler;
    private final DateTimeWebSocketHandler dateTimeWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(
                unixTimeWebSocketHandler,
                "/ws/time/unix")
            .setAllowedOrigins(allowedOrigins);

        registry.addHandler(
                dateTimeWebSocketHandler,
                "/ws/time/datetime")
            .setAllowedOrigins(allowedOrigins);
    }
}