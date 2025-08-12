package vn.ducthe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attribute_options")
@Getter
@Setter
public class AttributeOptionsEntity extends BaseEntity implements Serializable {

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attribute_id", nullable = false)
    private AttributesEntity attributesEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attributeOptionsEntity")
    private List<VariantAttributeOptionsEntity> variantAttributeOptionsEntities = new ArrayList<>();

}
