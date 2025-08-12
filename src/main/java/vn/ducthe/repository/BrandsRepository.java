package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.BrandsEntity;

@Repository
public interface BrandsRepository extends JpaRepository<BrandsEntity, Long> {
}
