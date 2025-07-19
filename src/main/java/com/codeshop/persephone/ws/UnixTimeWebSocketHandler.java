package com.codeshop.persephone.ws;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class UnixTimeWebSocketHandler extends BaseTextWebSocketHandler {

    public UnixTimeWebSocketHandler(ScheduledExecutorService scheduler) {
        super(scheduler);
    }

    @Override
    protected void startScheduledTask() {
        scheduledTask = schedule(this::broadcastUnixTime, 10);
    }

    private void broadcastUnixTime() {
        final String json = String.format("{\"unix\": %d}", Instant.now().getEpochSecond());
        broadcast(json);
    }
}
