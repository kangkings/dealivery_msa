package org.example.board.domain.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.company.model.entity.Company;
import org.example.board.domain.user.model.entity.User;

public class CompanyDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CompanySignupComplete {
        private Long idx;
        private String companyName;
        private String email;

        public Company toEntity() {
            return Company.builder()
                    .idx(this.idx)
                    .companyName(this.companyName)
                    .email(this.email)
                    .build();
        }
    }
}
