package vn.ducthe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "product_specifications")
@Getter
@Setter
public class ProductSpecificationsEntity extends BaseEntity implements Serializable {

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductsEntity productsEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "spec_id", nullable = false)
    private SpecificationsEntity specificationsEntity;
}
