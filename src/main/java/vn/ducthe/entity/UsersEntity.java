package vn.ducthe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UsersEntity extends BaseEntity implements Serializable {
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
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "status")
    private String status;

    // cascade nghĩa là khi xóa một đối tương cha thì trong bảng UserRole tương ứng cũng bị xóa.
    // Phải hiểu về orphanRemoval để làm gì mục đích của nó.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usersEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRolesEntity>  userRolesEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usersEntity")
    private List<ShopsEntity>  shopsEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usersEntity")
    private List<ReviewsEntity>  reviewsEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usersEntity")
    private List<ReviewRepliesEntity>  reviewRepliesEntities = new ArrayList<>();
}
