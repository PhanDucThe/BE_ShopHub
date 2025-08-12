package vn.ducthe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.io.Serializable;

@Entity
@Table(name = "review_replies")
@Getter
@Setter
public class ReviewRepliesEntity extends BaseEntity implements Serializable {

    @Column(name = "reply_text")
    private String replyText;

    @Column(name = "reply_type")
    private String replyType;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "review_id",  nullable = false)
    private ReviewsEntity reviewsEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",  nullable = false)
    private UsersEntity usersEntity;

}
