package vn.ducthe.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCreateRequest {

    @NotNull(message = "Các thông tin cơ bản của sản phẩm không được để trống!")
    @Valid
    private ProductBasicInfoRequest basicInfo;

    @NotEmpty(message = "Thông tin cụ thể của sản phẩm không được để trống!")
    @Valid
    private List<ProductVariantRequest> variants;

    @NotEmpty(message = "Các thông số kĩ thuật của sản phẩm không được để trống!")
    @Valid
    private List<ProductSpecificationRequest> specifications;
}
