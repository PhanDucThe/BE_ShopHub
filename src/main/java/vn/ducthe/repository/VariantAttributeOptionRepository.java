package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.VariantAttributeOptionEntity;

@Repository
public interface VariantAttributeOptionRepository extends JpaRepository<VariantAttributeOptionEntity,Long> {
}
