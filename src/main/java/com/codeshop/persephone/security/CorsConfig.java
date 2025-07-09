package com.codeshop.persephone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    private static final String[] ALLOWED_ORIGINS = { "https://persephone.run", "https://api.persephone.run" };

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@SuppressWarnings("NullableProblems") CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins(ALLOWED_ORIGINS)
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                    .allowedHeaders("*")
                    .exposedHeaders("ETag", "Content-Length", "Accept-Ranges", "Content-Range")
                    .maxAge(3600);
            }
        };
    }
}
