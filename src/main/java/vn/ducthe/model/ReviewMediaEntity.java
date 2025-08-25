package vn.ducthe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "review_media")
@Getter
@Setter
public class ReviewMediaEntity extends BaseEntity implements Serializable {

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "thumbnail_path")
    private String thumbnailPath;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "status")
    private String status;

    @Column(name = "is_approved")
    private String isApproved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewEntity;


}
