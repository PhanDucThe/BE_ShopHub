package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantAttributeOptionUpdateRequest {
    private Long variantAttributeOptionId; // ID của VariantAttributeOptionsEntity (có thể null nếu là mới)
    private Long attributeOptionId; // ID của AttributeOption được reference
}
