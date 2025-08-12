package vn.ducthe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.ducthe.dto.response.CategoryBaseDTO;
import vn.ducthe.dto.response.CategoryDTO;
import vn.ducthe.entity.CategoriesEntity;
import vn.ducthe.mapper.CategoryBaseMapper;
import vn.ducthe.mapper.CategoryMapper;
import vn.ducthe.repository.CategoriesRepository;
import vn.ducthe.service.CategoriesService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        // Get Category parent
        List<CategoriesEntity>  categories = categoryRepository.findByParentIsNull();
        return categoryMapper.toCategoryDTO(categories);
    }
}
