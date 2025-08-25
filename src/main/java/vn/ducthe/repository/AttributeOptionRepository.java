package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.AttributeEntity;
import vn.ducthe.model.AttributeOptionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeOptionRepository extends JpaRepository<AttributeOptionEntity,Long> {
    Optional<AttributeOptionEntity> findByAttributeEntity_IdAndValue(Long attributeEntityId, String value);
    List<AttributeOptionEntity> findByAttributeEntity_Id(Long attributeEntityId);
}
