package com.codeshop.persephone.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeapLogger { // why so serious?
    private long lastUsed;
    private long lastCommitted;
    private final Runtime r = Runtime.getRuntime();
    private final long maxMemoryInMB = getMaxMemoryInMB();

    public HeapLogger() {
        lastUsed        = getUsedMemoryInMB();
        lastCommitted   = getCommittedMemoryInMB();
    }

    @Scheduled(fixedRate = 10_000)
    public void logHeapChange() {
        long used       = getUsedMemoryInMB();
        long committed  = getCommittedMemoryInMB();

        var logMessage = buildLog(used, committed);
        if (committed != lastCommitted || used < lastUsed) {
            log.info(logMessage);
        }
        lastUsed = used;
        lastCommitted = committed;
    }

    private String buildLog(long used, long committed) {
        StringBuilder sb = new StringBuilder(50);
        sb.append("Heap(MB) used=");
        if (used < lastUsed) {
            sb.append(lastUsed).append("->").append(used);
        } else {
            sb.append(used);
        }
        sb.append(" committed=");
        if (committed != lastCommitted) {
            sb.append(lastCommitted).append("->").append(committed);
        } else {
            sb.append(committed);
        }
        sb.append(" max=").append(maxMemoryInMB);
        return sb.toString();
    }

    private long getUsedMemoryInMB() {
        return (r.totalMemory() - r.freeMemory()) >> 20;
    }

    private long getCommittedMemoryInMB() {
        return r.totalMemory() >> 20;
    }

    private long getMaxMemoryInMB() {
        return r.maxMemory() >> 20;
    }
}
