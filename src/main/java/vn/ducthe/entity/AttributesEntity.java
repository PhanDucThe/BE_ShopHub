package vn.ducthe.entity;

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
public class AttributesEntity extends BaseEntity implements Serializable {
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attributesEntity")
    private List<AttributeOptionsEntity> attributesOptionsEntities = new ArrayList<>();
}
