package com.codeshop.persephone.ws;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeWebSocketHandler extends BaseTextWebSocketHandler {
    private final DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    public DateTimeWebSocketHandler() {
        schedule(this::broadcastTime, 0, 1);
    }

    private void broadcastTime() {
        String datetime = formatter.format(Instant.now());
        String json = "{\"datetime\": \"" + datetime + "\"}";
        broadcast(json);
    }
}
