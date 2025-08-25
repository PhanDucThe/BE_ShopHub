package vn.ducthe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ducthe.model.UserRoleEntity;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {
}
