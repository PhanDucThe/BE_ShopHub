package vn.ducthe.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantImageCreateRequest {

    @NotBlank(message = "Ảnh không được để trống!")
    private String url;
    private String alt;
    private Integer sortOrder;
}
