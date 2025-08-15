package vn.ducthe.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import vn.ducthe.common.SalePriceLessThanOriginalPrice;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SalePriceLessThanOriginalPrice
public class ProductVariantCreatRequest {

    @NotBlank(message = "Mã SKU không được để trống!")
    @Size(max = 50, message = "Mã SKU không được vượt quá 50 ký tự!")
    private String skuCode;

    @NotBlank(message = "Tên biến thể không được để trống!")
    private String variantName;

    @NotNull(message = "Giá gốc không được để trống!")
    @Positive(message = "Giá gốc phải lớn hơn 0!")
    private Double originalPrice;

    @NotNull(message = "Giá bán không được để trống!")
    @PositiveOrZero(message = "Giá bán không được nhỏ hơn 0!")
    private Double salePrice;

    @NotNull(message = "Số lượng tồn kho không được để trống!")
    @PositiveOrZero(message = "Số lượng tồn kho được không nhỏ hơn 0!")
    private Integer stock;

    @NotEmpty(message = "Các thuộc tính của sản phẩm không được để trống!")
    private List<Long> attributeOptionIds = new ArrayList<>();

    @NotEmpty(message = "Ảnh của sản phẩm không được để trống!")
    @Valid // Bắt buộc validate luôn class VariantImage bên trong
    private List<VariantImageCreateRequest> images =  new ArrayList<>();
}
