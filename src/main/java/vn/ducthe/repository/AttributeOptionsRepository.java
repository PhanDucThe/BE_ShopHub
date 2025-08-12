package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.AttributeOptionsEntity;

import java.util.Optional;

@Repository
public interface AttributeOptionsRepository extends JpaRepository<AttributeOptionsEntity, Long> {
    Optional<AttributeOptionsEntity> findById(Long id);
}
