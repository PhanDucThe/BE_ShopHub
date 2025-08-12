package vn.ducthe.mapper;

import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.CategoryBaseDTO;
import vn.ducthe.entity.CategoriesEntity;

@Component
public class CategoryBaseMapper {
    public CategoryBaseDTO categoryBaseDTO(CategoriesEntity categoriesEntity) {
        CategoryBaseDTO categoryBaseDTO = new CategoryBaseDTO();
        categoryBaseDTO.setName(categoriesEntity.getName());
        categoryBaseDTO.setDescription(categoriesEntity.getDescription());
        categoryBaseDTO.setSlug(categoriesEntity.getSlug());
        categoryBaseDTO.setImage(categoriesEntity.getImage());
        return categoryBaseDTO;
    }
}
