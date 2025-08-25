package vn.ducthe.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productSlug;
    private String categoryName;
    private String brandName;
    private String productImagesMain;
    private ReviewDTO reviews;
    private PriceDTO price;
    private Integer sold;
    private Integer stock;
    private String status;
    private String detailUrl;
}
