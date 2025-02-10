package com.example.springboot.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
	@Override
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/policies") // Or "/**" for all endpoints
                .allowedOrigins("http://localhost:8080") // Replace with your frontend URL if different
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify allowed methods
                .allowedHeaders("*"); // Allow all headers (or specify)
    }
	
	

	
}
