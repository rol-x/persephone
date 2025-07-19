package com.codeshop.persephone.rest;

import com.codeshop.persephone.connections.ConnectionsGame;
import com.codeshop.persephone.connections.Connections;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConnectionsController {
    private final ConnectionsGame connectionsGame;

    @GetMapping("/connections/daily")
    public Connections dailyConnectionsGame() {
        return connectionsGame.daily();
    }
}
