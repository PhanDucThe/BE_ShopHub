package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.ProductSpecificationsEntity;

@Repository
public interface ProductSpecificationsRepository extends JpaRepository<ProductSpecificationsEntity, Long> {
}
