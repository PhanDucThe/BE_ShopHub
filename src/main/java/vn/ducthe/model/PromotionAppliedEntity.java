package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "promotion_applies")
@Getter
@Setter
public class PromotionAppliedEntity extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "promotion_id")
    private PromotionEntity promotionEntity;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;


    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private BrandEntity brandEntity;


}
