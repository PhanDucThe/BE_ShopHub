package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.entity.ShopsEntity;

@Repository
public interface ShopsRepository extends JpaRepository<ShopsEntity,Long> {
}
