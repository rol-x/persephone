package com.codeshop.persephone.rest.dto;

import com.codeshop.persephone.connections.ConnectionsGame;
import com.codeshop.persephone.connections.ConnectionsGroup;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.shuffle;

public record ConnectionsPuzzle(int gameId, List<String> words, List<String> authors) {

    public static ConnectionsPuzzle fromGame(ConnectionsGame game) {
        int gameId = game.getGameId();
        var authors = game.getAuthors().split(",");
        List<String> words = game.getGroups().stream()
            .map(ConnectionsGroup::getWords)
            .flatMap(Set::stream)
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    shuffle(list);
                    return list;
                }
            ));
        return new ConnectionsPuzzle(gameId, words, List.of(authors));
    }
}
