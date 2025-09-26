package com.parking.lot.reservation.config; // Adjust the package name as needed

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global Configuration to enable Cross-Origin Resource Sharing (CORS).
 * This is necessary when the frontend (index.html, running locally)
 * attempts to connect to the backend (http://localhost:8080).
 */
@Configuration
public class CorsConfig {

    /**
     * Configures CORS to allow all necessary origins and methods.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Allows all paths under /api/v1 (your endpoint path)
                registry.addMapping("/api/v1/**")
                        // The "*" wildcard allows requests from any origin, 
                        // which is crucial when running the HTML file locally 
                        // (as it runs from the "file://" protocol).
                        .allowedOrigins("*") 
                        // Allows all HTTP methods required for the app (GET, POST, DELETE)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // Allows all headers
                        .allowedHeaders("*"); 
            }
        };
    }
}
