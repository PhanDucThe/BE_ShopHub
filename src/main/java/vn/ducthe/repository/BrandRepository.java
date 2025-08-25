package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.BrandEntity;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity,Long> {
}
