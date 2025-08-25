package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.ProductSpecificationEntity;

@Repository
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecificationEntity,Long> {
}
