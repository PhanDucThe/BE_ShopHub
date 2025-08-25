package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateProductRequest {
    private String name;
    private String slug;
    private String description;
    private Long categoryId;
    private Long brandId;
    private List<CreateProductVariantRequest> variants = new ArrayList<>();
    private List<CreateProductSpecificationRequest> specifications = new ArrayList<>();
}
