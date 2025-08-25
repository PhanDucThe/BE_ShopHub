package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.VariantEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity,Long> {
    Optional<VariantEntity> findByProductEntity_IdAndOptionSignature(Long productEntityId, String optionSignature);
    List<VariantEntity> findByCreatedDateAfter(LocalDateTime localDateTime);
}
