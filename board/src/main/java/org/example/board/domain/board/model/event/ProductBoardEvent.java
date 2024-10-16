package org.example.board.domain.board.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProductBoardEvent {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardRegisterCompleteEvent {
        private Long idx;
        private String title;
        private Long companyIdx;
        private Integer discountRate;
        private String productThumbnailUrl;
        private List<Long> productIdxList;
    }
}
