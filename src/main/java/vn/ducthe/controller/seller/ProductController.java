package vn.ducthe.controller.seller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.ducthe.common.ApiResponse;
import vn.ducthe.dto.request.CreateProductRequest;
import vn.ducthe.service.ProductsService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class ProductController {

    private final ProductsService productsService;

    @PostMapping(value = "/v1/products")
    public ApiResponse<?> createProduct(@Valid @RequestBody CreateProductRequest productCreateRequest) {
        return new ApiResponse<>(HttpStatus.CREATED.value(), "create product success", productsService.createProduct(productCreateRequest));
    }


    @GetMapping(value = "/v1/products")
    public ApiResponse<?> getAllProducts() {
        return new ApiResponse<>(HttpStatus.OK.value(), "get all products successfully", productsService.getAllProducts());
    }
}
