package vn.ducthe.mapper;

import org.springframework.stereotype.Component;
import vn.ducthe.model.CategoryAttributeEntity;
import vn.ducthe.model.CategoryEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryAttributeMapper {

    public List<CategoryAttributeEntity> toCategoryAttributeEntities(CategoryEntity category) {
        return category.getCategoryAttributeEntities()
                .stream()
                .sorted(Comparator.comparingInt(CategoryAttributeEntity::getSortOrder))
                .toList();
    }
}
