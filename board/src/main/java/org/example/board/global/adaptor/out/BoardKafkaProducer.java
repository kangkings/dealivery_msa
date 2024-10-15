package org.example.board.global.adaptor.out;

import lombok.RequiredArgsConstructor;
import org.example.board.domain.board.model.event.BoardRegisterEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendBoardRegisterEvent(BoardRegisterEvent event) {
        kafkaTemplate.send("board-register", event);
    }
}

