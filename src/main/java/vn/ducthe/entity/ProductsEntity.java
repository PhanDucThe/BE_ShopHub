package vn.ducthe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductsEntity extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopsEntity shopsEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoriesEntity categoriesEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandsEntity brandsEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productsEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VariantsEntity> variantsEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productsEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSpecificationsEntity> productSpecificationsEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productsEntity")
    private List<PromotionAppliesEntity> promotionAppliesEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productsEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewsEntity> reviewsEntities = new ArrayList<>();
}
