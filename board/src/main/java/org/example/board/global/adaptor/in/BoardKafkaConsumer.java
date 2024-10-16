package org.example.board.global.adaptor.in;

import lombok.AllArgsConstructor;
import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.board.model.event.BoardRegisterEvent;
import org.example.board.domain.board.repository.ProductBoardRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BoardKafkaConsumer {

    @KafkaListener(topics = "board-register", groupId = "board_group")
    public void consumeBoardRegisterEvent(BoardRegisterEvent event) {
    }
}

