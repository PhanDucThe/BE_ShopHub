package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.ImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
    ImageEntity findByVariantEntity_IdAndSortOrder(Long variantsEntityId, Integer sortOrder);
}
