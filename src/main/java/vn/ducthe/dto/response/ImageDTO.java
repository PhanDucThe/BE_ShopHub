package vn.ducthe.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
    private String url;
    private String alt;
    private Integer sortOrder;
}
