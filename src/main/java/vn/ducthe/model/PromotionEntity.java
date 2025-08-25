package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "promotions")
@Getter
@Setter
public class PromotionEntity extends BaseEntity implements Serializable {

    @Column(name = "promotion_code")
    private String promotionCode;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_value")
    private Double discountValue;

    @Column(name = "max_discount_amount")
    private Double maxDiscountAmount;

    @Column(name = "min_order_value")
    private Double minOrderValue;

    @Column(name = "applies_to")
    private String appliesTo;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "usage_limit")
    private Integer usageLimit;

    @Column(name = "usage_count")
    private Integer usageCount;

    @Column(name = "per_user_limit")
    private Integer perUserLimit;

    @Column(name = "promotion_type")
    private String promotionType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PromotionAppliedEntity> promotionAppliedEntities =  new ArrayList<>();
}
