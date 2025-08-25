package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity extends BaseEntity  implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roleEntity")
    private Set<UserRoleEntity> userRoles = new HashSet<>();

}
