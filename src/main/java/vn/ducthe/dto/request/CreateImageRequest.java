package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateImageRequest {
    private String url;
    private String alt;
    private Integer sortOrder = 1;
}
