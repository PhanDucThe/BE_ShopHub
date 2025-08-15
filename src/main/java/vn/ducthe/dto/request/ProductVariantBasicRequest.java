package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVariantBasicRequest {
    private String skuCode;
    private String variantName;
    private Double originalPrice;
    private Double salePrice;
    private Integer stock;
}
