package vn.ducthe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "variants")
@Getter
@Setter
public class VariantsEntity extends BaseEntity implements Serializable {

    @Column(name = "sku_code")
    private String skuCode;

    @Column(name = "variant_name")
    private String variantName;

    @Column(name = "original_price")
    private Double originalPrice;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "sold")
    private Integer sold;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id",  nullable = false)
    private ProductsEntity productsEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "variantsEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagesEntity> imagesEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "variantsEntity",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VariantAttributeOptionsEntity> variantAttributeOptionsEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "variantsEntity")
    private List<ReviewsEntity> reviewsEntities = new ArrayList<>();

}
