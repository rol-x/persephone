package com.codeshop.persephone.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/time")
public class TimeController {

    @GetMapping("/unix")
    public long time() {
        return Instant.now().getEpochSecond();
    }
}
