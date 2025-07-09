package com.codeshop.persephone.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class OpenApiController {

    @GetMapping({
        "/swagger",
        "/help",
        "/api",
        "/docs",
        "/info",
        "/api-docs"})
    public ResponseEntity<Void> redirectToSwagger() {
        return ResponseEntity.status(302).location(URI.create("/swagger-ui.html")).build();
    }
}
