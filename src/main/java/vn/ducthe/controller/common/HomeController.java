package vn.ducthe.controller.common;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ducthe.common.ApiResponse;
import vn.ducthe.service.ProductsService;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class HomeController {

    private final ProductsService productsService;

    @GetMapping(value = "/v1/product-top-deals")
    public ApiResponse<?> getProductTopDeals() {
        return new  ApiResponse<>(200, "success", productsService.getProductsTopDeal());
    }

    @GetMapping(value = "/v1/products/category/{slug}")
    public ApiResponse<?> getProductsByCategory(@PathVariable String slug) {
        return new ApiResponse<>(200, "success", productsService.getProductsByCategory(slug));
    }
}
