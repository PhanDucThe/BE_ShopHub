package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateImageRequest {
    private String imageUrl;
    private String imageAlt;
    private Integer sortOrder;
}
