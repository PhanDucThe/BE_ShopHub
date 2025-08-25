package vn.ducthe.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ProductDetailDTO {
    private Long productId;
    private String productName;
    private String slug;
    private String description;
    private BrandDTO brand;
    private CategoryDTO category;
    private List<SpecificationDTO> specification = new ArrayList<>();
    private VariantDTO currentVariant;
    private Map<String, Object> availableOptions = new TreeMap<>(); // Nested như {"storages": [ {value, slug, available, colors: [...] } ]}
}
