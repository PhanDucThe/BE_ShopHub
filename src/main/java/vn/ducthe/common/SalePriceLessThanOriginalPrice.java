package vn.ducthe.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SalePriceLessThanOriginalPriceValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SalePriceLessThanOriginalPrice {
    String message() default "Giá bán phải nhỏ hơn hoặc bằng giá gốc!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
