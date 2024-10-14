package org.example.qna.domain.board.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.qna.domain.company.model.entity.Company;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBoard {
    @Id
    private Long idx;
    private String Title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_idx")
    private Company company;
}
