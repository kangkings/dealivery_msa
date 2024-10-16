package org.example.orders.domain.company.model.dto;

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
//        private List<ProductBoardDto.ProductBoardResponse> productBoards;
    }
}
