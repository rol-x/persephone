package com.codeshop.persephone.rest;

import com.codeshop.persephone.connections.ConnectionsGame;
import com.codeshop.persephone.connections.ConnectionsService;
import com.codeshop.persephone.rest.dto.ConnectionsPuzzle;
import com.codeshop.persephone.rest.dto.GuessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
public class ConnectionsController {
    private final ConnectionsService connectionsService;

    @GetMapping("/daily")
    public ConnectionsGame dailyConnectionsPuzzle() {
        return connectionsService.daily();
    }

    @GetMapping("/daily/v2")
    public ConnectionsPuzzle dailyConnectionsPuzzleV2() {
        return ConnectionsPuzzle.fromGame(
            connectionsService.daily()
        );
    }

    @PostMapping("/{gameId}/check")
    public GuessResponse checkGroupGuess(@PathVariable Long gameId, @RequestBody String groupGuess) {
        return connectionsService.check(gameId, groupGuess)
            .map(GuessResponse::correct)
            .orElseGet(GuessResponse::incorrect);
    }
}
