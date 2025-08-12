package vn.ducthe.controller.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ducthe.common.ApiResponse;
import vn.ducthe.service.CategoriesService;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoriesService categoryService;

    @GetMapping(value = "/v1/categories")
    public ApiResponse<?> getAllCategories () {
        return new ApiResponse<>(HttpStatus.OK.value(), "success", categoryService.getAllCategories());
    }
}
