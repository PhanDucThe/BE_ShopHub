package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity extends BaseEntity implements Serializable {
    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "shipping_fee")
    private Double shippingFee;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "total")
    private Double total;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "notes")
    private String notes;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "promotion_id")
    private PromotionEntity promotionEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shipping_address_id")
    private AddresseEntity addressShippingEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_address_id")
    private AddresseEntity addressBillingEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItemEntities = new ArrayList<>();
}
