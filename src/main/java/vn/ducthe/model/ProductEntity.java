package vn.ducthe.model;

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
public class ProductEntity extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private BrandEntity brandEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    List<VariantEntity> variantEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductSpecificationEntity> productSpecificationEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity")
    List<ReviewEntity> reviewEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity")
    List<PromotionAppliedEntity> promotionAppliedEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ImageEntity> imageEntities = new ArrayList<>();
}
