package org.example.auth.domain.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.auth.domain.company.model.entity.Company;

public class CompanyDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CompanySignupComplete {
        private Long idx;
        private String email;
        private String password;
        private String role;

        public Company toEntity() {
            return Company.builder()
                    .idx(this.idx)
                    .email(this.email)
                    .password(this.password)
                    .role(this.role)
                    .build();
        }
    }
}
