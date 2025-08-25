package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateProductVariantRequest {
    private String skuCode;
    private String variantName;
    private Double originalPrice;
    private Double salePrice;
    private Integer stock;
    private List<CreateAttributesRequest> attributes = new ArrayList<>();
    private List<CreateImagesRequest> images = new ArrayList<>();
}
