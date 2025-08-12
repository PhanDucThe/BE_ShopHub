package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.VariantsEntity;

@Repository
public interface VariantsRepository extends JpaRepository<VariantsEntity, Long> {

}
