package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specifications")
@Getter
@Setter
public class SpecificationEntity extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specificationEntity")
    private List<ProductSpecificationEntity> productSpecificationEntities =  new ArrayList<>();

}
