package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "review_replies")
@Getter
@Setter
public class ReviewReplieEntity extends BaseEntity implements Serializable {

    @Column(name = "reply_text")
    private String replyText;

    @Column(name = "reply_type")
    private String replyType;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
