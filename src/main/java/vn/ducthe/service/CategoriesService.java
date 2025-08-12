package vn.ducthe.service;

import vn.ducthe.dto.response.CategoryDTO;
import vn.ducthe.entity.CategoriesEntity;

import java.util.List;

public interface CategoriesService {
    List<CategoryDTO> getAllCategories();
}
