package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class AddresseEntity extends BaseEntity implements Serializable {

    @Column(name = "type")
    private String type;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addressShippingEntity")
    private List<OrderEntity> orderShippingEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addressBillingEntity")
    private List<OrderEntity> orderBillingEntities = new ArrayList<>();
}
