package memoire.com.memoirelisence.configuration;

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
                registry.addMapping("/**") // Permet toutes les routes
                        .allowedOrigins("http://localhost:5173") // Autorise Vite React
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")// Autorise ces méthodes HTTP
                        .allowedHeaders("*")// Autorise tous les headers
                        .allowCredentials(true);
            }
        };
    }
}
