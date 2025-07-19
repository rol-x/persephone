package com.codeshop.persephone.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class DateTimeWebSocketHandler extends BaseTextWebSocketHandler {
    private static final DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Scheduled(fixedRate = 1000)
    protected void broadcastDateTime() {
        if (sessions.isEmpty()) return;
        final String datetime = formatter.format(Instant.now());
        final String json = String.format("{\"datetime\": \"%s\"}", datetime);
        log.info("[DateTime] Created message: {}", json);
        broadcast(json);
    }
}
