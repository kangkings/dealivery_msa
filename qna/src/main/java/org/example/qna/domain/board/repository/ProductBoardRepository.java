package org.example.qna.domain.board.repository;

import org.example.qna.domain.board.model.entity.ProductBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductBoardRepository extends JpaRepository<ProductBoard, Long> {
    List<ProductBoard> findByCompanyEmail(String companyEmail);
}
