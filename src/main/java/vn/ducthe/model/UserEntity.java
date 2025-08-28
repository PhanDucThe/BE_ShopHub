package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.ducthe.enums.Gender;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity implements Serializable {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "status")
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRoleEntity> userRoles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<ReviewEntity> reviews = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<ReviewReplieEntity> reviewRepliesEntities =  new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddresseEntity> addressesEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartEntity> cartEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<OrderEntity> orderEntities = new ArrayList<>();
}
