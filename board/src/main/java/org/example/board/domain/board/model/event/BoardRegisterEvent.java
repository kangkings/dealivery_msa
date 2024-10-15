package org.example.board.domain.board.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.company.model.entity.Company;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRegisterEvent {
    private String title;
    private Company company;
    private Integer discountRate;
    private String productThumbnailUrl;
    private String productDetailUrl;

    // toEntity 메서드를 통해 ProductBoard로 변환
    public ProductBoard toEntity() {
        return ProductBoard.builder()
                .title(this.title)
                .company(this.company)
                .discountRate(this.discountRate)
                .productThumbnailUrl(this.productThumbnailUrl)
                .productDetailUrl(this.productDetailUrl)
                .build();
    }

    // fromEntity 메서드를 통해 이벤트를 생성
    public static BoardRegisterEvent fromEntity(ProductBoard productBoard) {
        return BoardRegisterEvent.builder()
                .title(productBoard.getTitle())
                .company(productBoard.getCompany())
                .discountRate(productBoard.getDiscountRate())
                .productThumbnailUrl(productBoard.getProductThumbnailUrl())
                .productDetailUrl(productBoard.getProductDetailUrl())
                .build();
    }
}
