package org.example.orders.global.adaptor.in;

import lombok.RequiredArgsConstructor;
import org.example.orders.domain.user.model.dto.UserDto;
import org.example.orders.domain.user.model.entity.User;
import org.example.orders.domain.user.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrdersKafkaConsumer {
    private final UserRepository userRepository;

    @KafkaListener(topics = "user_signup_complete", groupId = "orders_group")
    public void user_signup_complete(UserDto.UserSignupComplete userSignupComplete) {
        User newUser = userSignupComplete.toEntity();
        userRepository.save(newUser);
    }
}
