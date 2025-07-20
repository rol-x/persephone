package com.codeshop.persephone.rest.dto;

import com.codeshop.persephone.connections.ConnectionsGame;
import com.codeshop.persephone.connections.ConnectionsGroup;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record ConnectionsPuzzle(Long gameId, Set<String> words, List<String> authors) {

    public static ConnectionsPuzzle fromGame(ConnectionsGame game) {
        var gameId = game.getGameId();
        var authors = game.getAuthors().split(",");
        var words = game.getGroups().stream()
            .map(ConnectionsGroup::getWords)
            .flatMap(Set::stream)
            .collect(Collectors.toSet());

        return new ConnectionsPuzzle(gameId, words, List.of(authors));
    }
}
