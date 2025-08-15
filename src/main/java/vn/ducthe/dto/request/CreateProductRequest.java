package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateProductRequest {

    private Long shopId;
    private Long categoryId;
    private Long brandId;
    private String name;
    private String slug;
    private String description;
    private List<CreateVariantRequest> variants = new ArrayList<>();
    private List<CreateSpecificationRequest> specifications = new ArrayList<>();

}
