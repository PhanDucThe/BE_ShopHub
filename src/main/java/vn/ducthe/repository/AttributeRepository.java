package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.AttributeEntity;

import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity,Long> {
    Optional<AttributeEntity> findByNameEn(String storageAttrName);
}
