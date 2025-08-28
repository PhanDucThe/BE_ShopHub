package vn.ducthe.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class VariantDTO {
    private Long variantId;
    private Double originalPrice;
    private Double salePrice;
    private Integer stock;
    private Integer sold;
    private String image;
    private Map<String, String> selectOption = new HashMap<>();
}
