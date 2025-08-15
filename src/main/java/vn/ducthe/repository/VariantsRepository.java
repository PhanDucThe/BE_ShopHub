package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.VariantsEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VariantsRepository extends JpaRepository<VariantsEntity, Long> {
    List<VariantsEntity> findByCreatedDateAfter(LocalDateTime localDateTime);
}
