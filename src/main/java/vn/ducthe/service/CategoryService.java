package vn.ducthe.service;

import vn.ducthe.dto.response.FindCategoryDTO;

import java.util.List;

public interface CategoryService {
    List<FindCategoryDTO> getAllCategories();
}
