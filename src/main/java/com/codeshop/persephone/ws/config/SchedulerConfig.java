package com.codeshop.persephone.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class SchedulerConfig {

    @Bean(name = "scheduler", destroyMethod = "shutdown")
    public ScheduledExecutorService sharedScheduler() {
        return Executors.newScheduledThreadPool(2);
    }
}
