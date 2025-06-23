package com.example.passfashion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                // ❌ Không dùng setAllowedOrigins("*") khi allowCredentials = true
                .setAllowedOriginPatterns(
                        "http://localhost:3000",
                        "http://localhost:8081",
                        "http://localhost:8080",
                        "http://localhost:5173",
                        "http://192.168.1.8:3000"
                )
                .withSockJS(); // ⚠️ giữ lại nếu dùng SockJS ở client
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // dùng cho client subscribe
        registry.setApplicationDestinationPrefixes("/app"); // dùng khi client gửi message
    }
}
