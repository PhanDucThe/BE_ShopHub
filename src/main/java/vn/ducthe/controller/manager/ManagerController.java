package vn.ducthe.controller.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ducthe.common.ApiResponse;
import vn.ducthe.dto.request.CreateProductRequest;
import vn.ducthe.service.ProductsService;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ManagerController {

    private final ProductsService productsService;

    @PostMapping(value = "/v1/products")
    public ApiResponse<?> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return new  ApiResponse<>(200, "success", productsService.createProduct(createProductRequest));
    }

}
