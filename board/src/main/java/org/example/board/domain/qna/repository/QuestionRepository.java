package org.example.board.domain.qna.repository;

import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.qna.model.entity.Question;
import org.example.board.domain.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByProductBoardIn(List<ProductBoard> productBoards, Pageable pageable);
    Page<Question> findByUser(User user, Pageable pageable);
    Page<Question> findByProductBoard_Idx(Long productBoardIdx, Pageable pageable);
}
