package com.codeshop.persephone.ws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseTextWebSocketHandler extends TextWebSocketHandler {
    protected final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("New WebSocket connection established: {}", session.getId());
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("WebSocket connection closed: {}", session.getId());
        sessions.remove(session);
    }

    protected void broadcast(String json) {
        log.info("Broadcasting message: {}", json);
        sessions.removeIf(s -> !s.isOpen());
        if (sessions.isEmpty()) {
            log.info("No WebSocket sessions to broadcast to");
            return;
        }

        TextMessage message = new TextMessage(json);
        for (WebSocketSession session : sessions) {
            try {
                log.info("Sending message {} to {}", message, session.getId());
                session.sendMessage(message);
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
            }
        }
    }
}
