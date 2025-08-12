package vn.ducthe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shops")
@Getter
@Setter
public class ShopsEntity extends BaseEntity implements Serializable {

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description")
    private String description;

    @Column(name = "logo")
    private String logo;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",  nullable = false)
    private UsersEntity usersEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shopsEntity")
    private List<ProductsEntity> productsEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shopsEntity")
    private List<PromotionsEntity> promotionsEntities = new ArrayList<>();

}
