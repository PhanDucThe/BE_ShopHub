package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.SpecificationEntity;

@Repository
public interface SpecificationRepository extends JpaRepository<SpecificationEntity,Long> {
    SpecificationEntity findByName(String name);
}
