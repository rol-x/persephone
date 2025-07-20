package com.codeshop.persephone.time.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
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
public abstract class BaseTextWebSocketHandler extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    protected static final ObjectMapper mapper = new ObjectMapper();

    private static final int BROADCAST_RATE_MS = 1000;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("New WebSocket connection established: {}", session.getId());
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, @NonNull CloseStatus status) {
        log.info("WebSocket connection closed: {} (status={})", session.getId(), status.getCode());
        sessions.remove(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.warn("WebSocket transport error: {} (message: {})", session.getId(), exception.getMessage());
        sessions.remove(session);
    }

    protected abstract String createMessage() throws JsonProcessingException;

    @Scheduled(fixedRate = BROADCAST_RATE_MS)
    private void broadcastMessage() {
        if (noSessions()) return;
        try {
            broadcast(createMessage());
        } catch (JsonProcessingException e) {
            log.error("JSON serialization error", e);
        }
    }

    private boolean noSessions() {
        sessions.removeIf(s -> !s.isOpen());
        return sessions.isEmpty();
    }

    private void broadcast(String json) {
        TextMessage message = new TextMessage(json);
        for (WebSocketSession session : sessions) {
            try {
                log.debug("Sending message {} to {}", message, session.getId());
                session.sendMessage(message);
            } catch (IOException e) {
                log.warn("Failed send to {}: {}", session.getId(), e.getMessage());
            }
        }
    }
}
