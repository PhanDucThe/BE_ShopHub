package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class ReviewEntity extends BaseEntity implements Serializable {

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "title")
    private String title;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "helpful_count")
    private Integer helpfulCount;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "variant_id")
    private VariantEntity variantEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewMediaEntity> reviewMediaEntities =  new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewReplieEntity> reviewReplieEntities =  new ArrayList<>();
}
