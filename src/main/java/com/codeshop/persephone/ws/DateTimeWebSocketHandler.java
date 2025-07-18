package com.codeshop.persephone.ws;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class DateTimeWebSocketHandler extends BaseTextWebSocketHandler {
    private static final DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    public DateTimeWebSocketHandler(ScheduledExecutorService scheduler) {
        super(scheduler);
    }

    @Override
    protected void startScheduledTask() {
        scheduledTask = schedule(this::broadcastTime, 0, 1);
    }

    private void broadcastTime() {
        String datetime = formatter.format(Instant.now());
        String json = "{\"datetime\": \"" + datetime + "\"}";
        broadcast(json);
    }
}
