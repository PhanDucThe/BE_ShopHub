package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "variant_attribute_options")
@Getter
@Setter
public class VariantAttributeOptionEntity extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "variant_id")
    private VariantEntity variantEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "option_id")
    private AttributeOptionEntity attributeOptionEntity;
}
