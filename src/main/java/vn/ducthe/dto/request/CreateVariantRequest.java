package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateVariantRequest {
    private String skuCode;
    private String variantName;
    private Double originalPrice;
    private Double salePrice;
    private Integer stock;
    private List<Long> attributeOptionIds;
    private List<CreateImageRequest> images = new ArrayList<>();
}
