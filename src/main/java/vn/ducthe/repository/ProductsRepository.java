package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.ProductsEntity;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {
}
