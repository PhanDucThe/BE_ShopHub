package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attributes")
@Getter
@Setter
public class AttributeEntity extends BaseEntity implements Serializable {

    @Column(name = "name_vi")
    private String nameVi;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "is_variant")
    private Boolean isVariant;

    @Column(name = "input_type")
    private String inputType;

    @Column(name = "is_required")
    private Boolean isRequired;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attributeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttributeOptionEntity> attributeOptions = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attributeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryAttributeEntity> categoryAttributeEntities = new ArrayList<>();
}
