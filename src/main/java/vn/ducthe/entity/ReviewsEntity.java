package vn.ducthe.entity;

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
public class ReviewsEntity extends BaseEntity implements Serializable {

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
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity usersEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id",  nullable = false)
    private ProductsEntity productsEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "variant_id", nullable = false)
    private VariantsEntity variantsEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewsEntity")
    private List<ReviewMediaEntity> reviewMediaEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewsEntity")
    private List<ReviewRepliesEntity>  reviewRepliesEntities = new ArrayList<>();
}
