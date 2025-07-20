package com.codeshop.persephone.time.rest;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/random")
public class RandomnessController {
    private static final List<String> COIN_SIDES = List.of("heads", "tails");

    @GetMapping("/coinflip")
    public String coinflip() {
        return COIN_SIDES.get(randomRange(0, 1));
    }

    @GetMapping("/d6")
    public int rollD6() {
        return randomRange(1, 6);
    }

    @GetMapping("/{limit}")
    public int random(@PathVariable int limit) {
        return randomRange(0, limit);
    }

    @GetMapping("/{lower}/{upper}")
    public int randomInRange(@PathVariable int lower, @PathVariable int upper) {
        return randomRange(lower, upper);
    }

    private static int randomRange(int lower, int upper) {
        return RandomUtils.secure().randomInt(lower, upper + 1);
    }
}
