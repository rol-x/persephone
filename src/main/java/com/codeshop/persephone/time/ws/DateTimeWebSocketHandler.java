package com.codeshop.persephone.time.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@Component
public class DateTimeWebSocketHandler extends BaseTextWebSocketHandler {
    private static final DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Override
    protected String createMessage() throws JsonProcessingException {
        final String datetime = formatter.format(Instant.now());
        return mapper.writeValueAsString(Map.of("datetime", datetime));
    }
}
