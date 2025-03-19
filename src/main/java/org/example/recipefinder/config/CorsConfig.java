package org.example.recipefinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Tillad CORS for alle endpoints
                        .allowedOrigins("http://localhost:3000") // Frontend URL (kan justeres)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Tillad de specifikke metoder
                        .allowedHeaders("*") // Tillad alle headers
                        .allowCredentials(true); // Tillad brug af cookies og credentials
            }
        };
    }
}
