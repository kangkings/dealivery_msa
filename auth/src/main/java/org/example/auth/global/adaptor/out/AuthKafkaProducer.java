package org.example.auth.global.adaptor.out;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendSignupMessage(String message) {
        kafkaTemplate.send("company_signup", message);
    }
}
