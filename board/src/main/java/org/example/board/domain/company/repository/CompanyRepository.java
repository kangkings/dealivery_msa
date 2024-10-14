package org.example.board.domain.company.repository;

import org.example.board.domain.company.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
