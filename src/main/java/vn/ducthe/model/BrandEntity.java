package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
public class BrandEntity extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "logo")
    private String logo;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brandEntity")
    private List<ProductEntity> products = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brandEntity")
    private List<PromotionAppliedEntity> promotionAppliedEntities = new ArrayList<>();
}
