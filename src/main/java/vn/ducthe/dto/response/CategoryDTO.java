package vn.ducthe.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private CategoryBaseDTO category;
    private List<CategoryBaseDTO> subcategories =  new ArrayList<>();
}
