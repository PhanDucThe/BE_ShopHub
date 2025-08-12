package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.ImagesEntity;

@Repository
public interface ImagesRepository extends JpaRepository<ImagesEntity, Long> {
    ImagesEntity findByVariantsEntity_IdAndSortOrder(Long variantId, Integer sortOrder);
}
