package com.codeshop.persephone.connections;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class ConnectionsGame {
    private final ConnectionsRepository connectionsRepository;

    public Connections daily() {
        var today = Instant.now().atZone(ZoneId.of("Europe/Warsaw")).toLocalDate();
        return connectionsRepository.findByDate(today);
    }
}
