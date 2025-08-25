package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateImagesRequest {
    private String imageUrl;
    private String imageAlt;
    private Integer sortOrder;
}
