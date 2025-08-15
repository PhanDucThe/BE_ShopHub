package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSpecificationUpdateRequest {
    private Long specId; // referencs để cập nhật
    private ProductSpecificationRequest specification;
}
