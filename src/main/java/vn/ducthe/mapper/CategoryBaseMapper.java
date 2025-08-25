package vn.ducthe.mapper;

import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.CategoryBaseDTO;
import vn.ducthe.model.CategoryEntity;

@Component
public class CategoryBaseMapper {
    public CategoryBaseDTO toCategoryBaseDTO(CategoryEntity categoryEntity) {
        CategoryBaseDTO categoryBaseDTO = new CategoryBaseDTO();
        categoryBaseDTO.setName(categoryEntity.getName());
        categoryBaseDTO.setDescription(categoryEntity.getDescription());
        categoryBaseDTO.setSlug(categoryEntity.getSlug());
        categoryBaseDTO.setImage(String.valueOf(categoryEntity.getImage()));
        return categoryBaseDTO;
    }
}
