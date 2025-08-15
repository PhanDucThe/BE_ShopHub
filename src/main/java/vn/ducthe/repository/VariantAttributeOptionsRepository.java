package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.VariantAttributeOptionsEntity;

@Repository
public interface VariantAttributeOptionsRepository extends JpaRepository<VariantAttributeOptionsEntity, Long> {
}
