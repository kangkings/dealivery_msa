package org.example.orders.domain.orders.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.orders.domain.board.model.entity.ProductBoard;
import org.example.orders.domain.company.model.entity.Company;
import org.example.orders.domain.product.model.entity.Product;

import java.util.List;

public class OrdersEvent {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardRegisterEvent {
        private Long idx;
        private String title;
        private Long companyIdx;
        private Integer discountRate;
        private String productThumbnailUrl;
        private List<Long> productIdxList;

        public ProductBoard toEntity(Company company, List<Product> products){
            return ProductBoard.builder()
                    .idx(this.idx)
                    .title(this.title)
                    .company(company)
                    .discountRate(this.discountRate)
                    .productThumbnailUrl(this.productThumbnailUrl)
                    .build();
        }

    }

}
