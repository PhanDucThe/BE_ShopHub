package vn.ducthe.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.ducthe.dto.request.ProductVariantRequest;

public class SalePriceLessThanOriginalPriceValidator implements ConstraintValidator<SalePriceLessThanOriginalPrice, ProductVariantRequest> {

    @Override
    public void initialize(SalePriceLessThanOriginalPrice salePriceLessThanOriginalPrice) {
    }

    @Override
    public boolean isValid(ProductVariantRequest productVariantRequest, ConstraintValidatorContext cxt) {
        if (productVariantRequest == null) return true; // khong check no rong vi da su dung @NotNull roi
        Double originalPrice = productVariantRequest.getOriginalPrice();
        Double salePrice = productVariantRequest.getSalePrice();
        if (originalPrice == null || salePrice == null) return true; // Bor qua khong check truong nay vi da co sai @NotNull roi
        return salePrice <= originalPrice;
    }
}
