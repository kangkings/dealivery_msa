package org.example.board.domain.qna.repository;

import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.qna.model.entity.Question;
import org.example.board.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByProductBoardIn(List<ProductBoard> productBoards);
    List<Question> findByUser(User user);
}
