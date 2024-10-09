package org.example.user.global.adaptor.in;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserKafkaConsumer {
    @KafkaListener(topics = "company_signup", groupId = "group_1")
    public void receive(String message) throws IOException {
        System.out.println("받은 메시지 : " + message);
    }
}
