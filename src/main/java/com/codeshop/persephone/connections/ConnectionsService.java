package com.codeshop.persephone.connections;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConnectionsService {
    private final ConnectionsRepository connectionsRepository;

    public ConnectionsGame daily() {
        var today = Instant.now().atZone(ZoneId.of("Europe/Warsaw")).toLocalDate();
        return connectionsRepository.findByDate(today);
    }

    public Optional<ConnectionsGroup> check(Long gameId, String groupGuess) {
        var words = Set.of(groupGuess.split(","));
        var game = connectionsRepository.findById(gameId).orElseThrow();
        return game.findGroup(words);
    }
}
