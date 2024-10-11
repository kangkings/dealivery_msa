package org.example.auth.domain.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
//        private String type;
    }
}
