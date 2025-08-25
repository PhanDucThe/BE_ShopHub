package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.CategoryBaseDTO;
import vn.ducthe.dto.response.FindCategoryDTO;
import vn.ducthe.model.CategoryEntity;
import vn.ducthe.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final CategoryRepository categoryRepository;
    private final CategoryBaseMapper categoryBaseMapper;

    public List<FindCategoryDTO> findCategoryDTOS(List<CategoryEntity> parents) {
        List<FindCategoryDTO> categoryDTOS = new ArrayList<>();

        parents.forEach(parent -> {
            FindCategoryDTO findCategoryDTO = new FindCategoryDTO();
            findCategoryDTO.setId(parent.getId());
            findCategoryDTO.setCategory(categoryBaseMapper.toCategoryBaseDTO(parent));

            // child
            List<CategoryEntity> children = categoryRepository.findByParentId(parent.getId());
            List<CategoryBaseDTO> childrenBaseDTO =  new ArrayList<>();
            children.forEach(child -> {
                CategoryBaseDTO  categoryBaseDTO = categoryBaseMapper.toCategoryBaseDTO(child);
                childrenBaseDTO.add(categoryBaseDTO);
            });
            findCategoryDTO.setSubcategories(childrenBaseDTO);

            categoryDTOS.add(findCategoryDTO);
        });

        return  categoryDTOS;
    }

}
