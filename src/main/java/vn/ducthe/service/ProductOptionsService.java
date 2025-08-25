package vn.ducthe.service;

import vn.ducthe.model.CategoryAttributeEntity;
import vn.ducthe.model.ProductEntity;

import java.util.List;
import java.util.Map;

public interface ProductOptionsService {
    Map<String, Object> buildAvailableOptions(ProductEntity product, List<CategoryAttributeEntity> categoryAttributeEntities);
}
