package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attribute_options")
@Getter
@Setter
public class AttributeOptionEntity extends BaseEntity implements Serializable {

    @Column(name = "value")
    private String value;

    @Column(name = "slug")
    private String slug;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attribute_id")
    private AttributeEntity attributeEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attributeOptionEntity")
    private List<VariantAttributeOptionEntity> variantAttributeOptionEntities =  new ArrayList<>();
}
