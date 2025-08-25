package vn.ducthe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.ducthe.common.ApiResponse;
import vn.ducthe.service.CategoryService;
import vn.ducthe.service.ProductsService;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class PublicController {

    private final ProductsService productsService;
    private final CategoryService categoryService;


    @GetMapping(value = "/v1/products/{productSlug}")
    public ApiResponse<?> getPhoneDetail(@PathVariable String productSlug, @RequestParam(required = false) Map<String, String> options ) {
        return new ApiResponse<>(HttpStatus.OK.value(), "success", productsService.getProductDetails(productSlug, options));
    }

    @GetMapping(value = "/v1/product-top-deals")
    public ApiResponse<?> getProductTopDeals() {
        return new ApiResponse<>(HttpStatus.OK.value(), "success", productsService.getProductsTopDeal());
    }

    @GetMapping(value = "/v1/new-products")
    public ApiResponse<?> getNewProducts() {
        return new ApiResponse<>(HttpStatus.OK.value(), "success", productsService.getNewProducts());
    }

    @GetMapping(value = "/v1/products/category/{slug}")
    public ApiResponse<?> getProductCategory(@PathVariable String slug) {
        return new ApiResponse<>(HttpStatus.OK.value(), "success", productsService.getProductsByCategory(slug));
    }

    @GetMapping(value = "/v1/categories")
    public ApiResponse<?> getCategories() {
        return new ApiResponse<>(HttpStatus.OK.value(), "success", categoryService.getAllCategories());
    }

}
