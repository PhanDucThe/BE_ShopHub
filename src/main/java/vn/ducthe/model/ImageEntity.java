package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "images")
@Getter
@Setter
public class ImageEntity extends BaseEntity implements Serializable {

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_alt")
    private String imageAlt;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "variant_id")
    private VariantEntity variantEntity;
}
