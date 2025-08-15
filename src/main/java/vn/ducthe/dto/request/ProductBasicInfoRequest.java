package vn.ducthe.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBasicInfoRequest {

    @NotNull(message = "Danh mục không được để trống!")
    private Long categoryId;

    @NotNull(message = "Thương hiệu không được để trống!")
    private Long brandId;

    @NotBlank(message = "Tên sản phẩm không được để trống!")
    private String name;

    private String description;
}
