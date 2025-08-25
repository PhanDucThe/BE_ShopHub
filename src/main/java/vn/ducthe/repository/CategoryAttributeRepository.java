package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.CategoryAttributeEntity;
import vn.ducthe.model.CategoryEntity;

import java.util.List;

@Repository
public interface CategoryAttributeRepository extends JpaRepository<CategoryAttributeEntity, Long> {
    List<CategoryAttributeEntity> findByCategoryEntity_IdAndIsVariant(Long categoryEntityId, Boolean isVariant);
}
