package org.example.company.global.adaptor.out;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendSignupMessage(String message) {
        kafkaTemplate.send("company_signup", message);
    }
}
