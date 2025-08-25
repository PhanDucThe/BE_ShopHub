package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "category_attributes")
@Getter
@Setter
public class CategoryAttributeEntity extends BaseEntity implements Serializable {

    @Column(name = "is_variant")
    private Boolean isVariant;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attribute_id")
    private AttributeEntity attributeEntity;
}
