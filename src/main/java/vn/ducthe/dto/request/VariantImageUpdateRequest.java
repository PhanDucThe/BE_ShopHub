package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantImageUpdateRequest {
    private Long imageId;
    private String url;
    private String alt;
}
