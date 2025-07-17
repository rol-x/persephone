package com.codeshop.persephone.ws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseTextWebSocketHandler extends TextWebSocketHandler {
    protected final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    protected final ScheduledExecutorService scheduler;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    protected void broadcast(String json) {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(json));
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    }

    protected void schedule(Runnable task, long initialDelay, long intervalSeconds) {
        scheduler.scheduleAtFixedRate(task, initialDelay, intervalSeconds, TimeUnit.SECONDS);
    }
}
