package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.CategoriesEntity;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity,Long> {
    List<CategoriesEntity> findByParentIsNull(); // Lay cha
    List<CategoriesEntity> findByParentId(Long parentId); // Lay cha theo con.
}
