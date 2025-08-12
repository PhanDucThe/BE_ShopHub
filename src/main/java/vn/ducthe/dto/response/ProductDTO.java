package vn.ducthe.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private Long productId;
    private String productName;
    private String categoryName;
    private String brandName;
    private String productImagesMain;
    private ReviewDTO reviews;
    private PriceDTO price;
    private Integer sold;
}
