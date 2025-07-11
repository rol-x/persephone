package com.codeshop.persephone.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(
                new UnixTimeWebSocketHandler(),
                "/ws/time/unix")
            .setAllowedOrigins(allowedOrigins);

        registry.addHandler(
                new DateTimeWebSocketHandler(), "/ws/time/datetime")
            .setAllowedOrigins(allowedOrigins);
    }
}