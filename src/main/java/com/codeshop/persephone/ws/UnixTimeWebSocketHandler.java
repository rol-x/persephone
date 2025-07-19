package com.codeshop.persephone.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
@Component
public class UnixTimeWebSocketHandler extends BaseTextWebSocketHandler {

    @Scheduled(fixedRate = 1000)
    protected void broadcastUnixTime() {
        if (sessions.isEmpty()) return;
        final String json = String.format("{\"unix\": %d}", Instant.now().getEpochSecond());
        log.info("[UnixTime] Created message: {}", json);
        broadcast(json);
    }
}
