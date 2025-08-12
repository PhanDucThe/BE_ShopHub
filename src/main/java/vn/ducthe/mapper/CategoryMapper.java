package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.CategoryBaseDTO;
import vn.ducthe.dto.response.CategoryDTO;
import vn.ducthe.entity.CategoriesEntity;
import vn.ducthe.repository.CategoriesRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final CategoryBaseMapper categoryBaseMapper;
    private final CategoriesRepository categoriesRepository;

    public List<CategoryDTO> toCategoryDTO(List<CategoriesEntity> parents) {
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (CategoriesEntity parent : parents) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(parent.getId());
            categoryDTO.setCategory(categoryBaseMapper.categoryBaseDTO(parent));

            // Cac con
            List<CategoriesEntity>  children = categoriesRepository.findByParentId(parent.getId());
            List<CategoryBaseDTO> childrenBaseDTO = new ArrayList<>();
            for (CategoriesEntity child : children) {
                CategoryBaseDTO categoryBaseDTOChild = categoryBaseMapper.categoryBaseDTO(child);
                childrenBaseDTO.add(categoryBaseDTOChild);
            }
            categoryDTO.setSubcategories(childrenBaseDTO);
            categoryDTOs.add(categoryDTO);
        }
        return categoryDTOs;
    }
}
