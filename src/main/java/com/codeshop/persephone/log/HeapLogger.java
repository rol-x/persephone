package com.codeshop.persephone.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeapLogger {
    private final Runtime r = Runtime.getRuntime();

    @Scheduled(fixedRate = 30_000)
    public void logHeap() {
        long used       = (r.totalMemory() - r.freeMemory()) >> 20;
        long committed  = r.totalMemory() >> 20;
        long max        = r.maxMemory() >> 20;
        log.info("Heap(MB) used={} committed={} max={}", used, committed, max);
    }
}
