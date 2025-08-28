package vn.ducthe.model;

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
public class VariantEntity extends BaseEntity implements Serializable {

    @Column(name = "slug")
    private String slug;

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

    @Column(name = "option_signature")
    private String optionSignature;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "variantEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VariantAttributeOptionEntity> variantAttributeOptionEntities =  new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "variantEntity")
    private List<ReviewEntity> reviewEntities =  new ArrayList<>();
}
