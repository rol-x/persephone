package com.codeshop.persephone.ws;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class UnixTimeWebSocketHandler extends BaseTextWebSocketHandler {

    public UnixTimeWebSocketHandler(ScheduledExecutorService scheduler) {
        super(scheduler);
        schedule(this::broadcastUnixTime, 0, 1);
    }

    private void broadcastUnixTime() {
        long unix = Instant.now().getEpochSecond();
        String json = "{\"unix\": " + unix + "}";
        broadcast(json);
    }
}