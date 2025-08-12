package vn.ducthe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class CategoriesEntity extends BaseEntity implements Serializable {
    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "status")
    private String status;

    // Categories cha
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<CategoriesEntity> children  = new ArrayList<>();

    // Categories con
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", nullable = false)
    private CategoriesEntity parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriesEntity")
    private List<ProductsEntity> productsEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriesEntity")
    private List<PromotionAppliesEntity> promotionAppliesEntities = new ArrayList<>();
}
