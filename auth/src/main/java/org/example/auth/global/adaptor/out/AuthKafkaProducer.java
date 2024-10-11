package org.example.auth.global.adaptor.out;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

//    public void sendSignupMessage(String message) {
//        kafkaTemplate.send("company_signup", message);
//    }

//    public void sendSignupMessage(Object signupComplete){
//        kafkaTemplate.send("user_signup_complete", signupComplete);
//    }
}
