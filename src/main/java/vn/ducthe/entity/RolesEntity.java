package vn.ducthe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RolesEntity extends BaseEntity implements Serializable {
    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rolesEntity")
    private List<UserRolesEntity> userRolesEntities = new ArrayList<>();
}
