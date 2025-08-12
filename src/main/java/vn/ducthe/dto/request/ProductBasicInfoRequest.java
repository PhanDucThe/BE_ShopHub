package vn.ducthe.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBasicInfoRequest {

    @NotNull(message = "Shop không được để trống!")
    private Long shopId; // Để biết sản phẩm này thuộc về shops nào

    @NotNull(message = "Danh mục không được để trống!")
    private Long categoryId;

    @NotNull(message = "Thương hiệu không được để trống!")
    private Long brandId;

    @NotBlank(message = "Tên sản phẩm không được để trống!")
    private String name;

    private String slug;
    private String description;
}
