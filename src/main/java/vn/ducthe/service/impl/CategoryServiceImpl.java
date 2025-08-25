package vn.ducthe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.ducthe.dto.response.FindCategoryDTO;
import vn.ducthe.mapper.CategoryMapper;
import vn.ducthe.model.CategoryEntity;
import vn.ducthe.repository.CategoryRepository;
import vn.ducthe.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<FindCategoryDTO> getAllCategories() {
        // Get Category parent
        List<CategoryEntity> categoryEntities = categoryRepository.findByParentIsNull();

        return categoryMapper.findCategoryDTOS(categoryEntities);
    }
}
