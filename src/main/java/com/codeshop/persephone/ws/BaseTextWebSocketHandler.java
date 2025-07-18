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
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseTextWebSocketHandler extends TextWebSocketHandler {
    protected final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    protected final ScheduledExecutorService scheduler;
    protected ScheduledFuture<?> scheduledTask;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        boolean wasEmpty = sessions.isEmpty();
        sessions.add(session);
        if (wasEmpty && scheduledTask == null) {
            startScheduledTask();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        if (sessions.isEmpty() && scheduledTask != null) {
            stopScheduledTask();
        }
    }

    protected abstract void startScheduledTask();

    protected void stopScheduledTask() {
        if (scheduledTask != null) {
            scheduledTask.cancel(false);
            scheduledTask = null;
            log.debug("Stopped scheduled task for {}", this.getClass().getSimpleName());
        }
    }

    protected void broadcast(String json) {
        if (sessions.isEmpty()) {
            return;
        }

        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(json));
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            } else {
                sessions.remove(session);
            }
        }
    }

    protected ScheduledFuture<?> schedule(Runnable task, long initialDelay, long intervalSeconds) {
        return scheduler.scheduleAtFixedRate(task, initialDelay, intervalSeconds, TimeUnit.SECONDS);
    }
}
