package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductVariantUpdateRequest {
    private Long variantId;
    private ProductVariantBasicRequest variants;
    private List<VariantAttributeOptionUpdateRequest> variantAttributeOptions =  new ArrayList<>();
    private List<VariantImageUpdateRequest> variantImageUpdates =  new ArrayList<>();
}
