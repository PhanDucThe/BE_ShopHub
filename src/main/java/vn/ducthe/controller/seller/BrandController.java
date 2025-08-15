package vn.ducthe.controller.seller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ducthe.common.ApiResponse;
import vn.ducthe.service.BrandsService;


@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class BrandController {
    private final BrandsService brandsService;

    @GetMapping(value = "/v1/brands")
    public ApiResponse<?> getAllBrands() {
        return new ApiResponse<>(HttpStatus.OK.value(), "success", brandsService.getAllBrands());
    }
}
