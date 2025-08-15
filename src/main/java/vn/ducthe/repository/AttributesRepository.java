package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.AttributesEntity;

@Repository
public interface AttributesRepository extends JpaRepository<AttributesEntity, Long> {
}
