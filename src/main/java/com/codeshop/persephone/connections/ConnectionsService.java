package com.codeshop.persephone.connections;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectionsService {
    private final ConnectionsRepository connectionsRepository;

    public ConnectionsGame daily() {
        var today = Instant.now().atZone(ZoneId.of("Europe/Warsaw")).toLocalDate().toString();
        return connectionsRepository.findByDate(today).orElseThrow();
    }

    public Optional<ConnectionsGroup> check(Integer gameId, Set<String> words) {
        return connectionsRepository.findById(gameId)
            .flatMap(game -> game.findGroup(words));
    }

    public int incrementSolved(Integer gameId) {
        return connectionsRepository.findById(gameId)
            .map(ConnectionsGame::incrementSolvedCount)
            .map(connectionsRepository::save)
            .map(ConnectionsGame::getSolvedBy)
            .orElse(0);
    }
}
