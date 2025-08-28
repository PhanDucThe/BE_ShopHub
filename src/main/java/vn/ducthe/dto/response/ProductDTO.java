package vn.ducthe.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productSlug;
    private Map<String, String> option = new HashMap<>();
    private String categoryName;
    private String brandName;
    private ReviewDTO reviews;
    private PriceDTO price;
    private Integer sold;
    private Integer stock;
    private String image;
    private String status;
}
