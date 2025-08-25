package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.CategoryEntity;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    List<CategoryEntity> findByParentIsNull();
    List<CategoryEntity> findByParentId(Long parent);
}
