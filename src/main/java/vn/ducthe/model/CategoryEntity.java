package vn.ducthe.model;

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
public class CategoryEntity extends BaseEntity implements Serializable {

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> children  = new ArrayList<>();

    // Categories con
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryEntity")
    private List<ProductEntity> products = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryEntity")
    private List<PromotionAppliedEntity> promotionAppliedEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryAttributeEntity> categoryAttributeEntities = new ArrayList<>();
}
