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
    private List<ImageDTO> images = new ArrayList<>();
    private VariantDTO currentVariant;
    private ReviewDTO review;
    private Map<String, Object> availableOptions = new TreeMap<>(); // Nested như {"storages": [ {value, slug, available, colors: [...] } ]}
}
