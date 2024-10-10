package org.example.user.global.adaptor.out;

import lombok.RequiredArgsConstructor;
import org.example.user.domain.user.model.dto.UserDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendSignupMessage(UserDto.UserSignupComplete userSignupComplete) {
        kafkaTemplate.send("user_signup_complete", userSignupComplete);
    }
}
