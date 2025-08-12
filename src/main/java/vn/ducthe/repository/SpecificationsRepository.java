package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.SpecificationsEntity;

import java.util.Optional;

@Repository
public interface SpecificationsRepository extends JpaRepository<SpecificationsEntity, Long> {
    Optional<SpecificationsEntity> findByName(String name);
}
