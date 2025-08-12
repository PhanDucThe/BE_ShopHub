package vn.ducthe.controller.seller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.ducthe.common.ApiResponse;
import vn.ducthe.dto.request.ProductCreateRequest;
import vn.ducthe.dto.request.ProductUpdateRequest;
import vn.ducthe.service.ProductsService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class ProductController {

    private final ProductsService productsService;

    @PostMapping(value = "/v1/products")
    public ApiResponse<?> createProduct(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        return new ApiResponse<>(HttpStatus.CREATED.value(), "create product success", productsService.createProduct(productCreateRequest));
    }

    @PutMapping(value = "/v1/products")
    public ApiResponse<?> updateProduct(@Valid @RequestBody ProductUpdateRequest productUpdateRequest) {
        productsService.updateProduct(productUpdateRequest);
        return new ApiResponse<>(HttpStatus.OK.value(), "update product successfully");
    }
}
