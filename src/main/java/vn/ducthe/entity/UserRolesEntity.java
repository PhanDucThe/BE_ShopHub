package vn.ducthe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class UserRolesEntity extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",  nullable = false)
    private UsersEntity usersEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",  nullable = false)
    private RolesEntity rolesEntity;
}
