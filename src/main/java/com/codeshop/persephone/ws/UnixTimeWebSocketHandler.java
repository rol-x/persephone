package com.codeshop.persephone.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Component
public class UnixTimeWebSocketHandler extends BaseTextWebSocketHandler {

    @Override
    protected String createMessage() throws JsonProcessingException {
        final long epoch = Instant.now().getEpochSecond();
        return mapper.writeValueAsString(Map.of("unix", epoch));
    }
}
