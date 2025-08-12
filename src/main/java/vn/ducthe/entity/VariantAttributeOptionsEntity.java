package vn.ducthe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "variant_attribute_options")
@Getter
@Setter
public class VariantAttributeOptionsEntity extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "variant_id",  nullable = false)
    private VariantsEntity variantsEntity;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "option_id",  nullable = false)
    private AttributeOptionsEntity attributeOptionsEntity;
}
