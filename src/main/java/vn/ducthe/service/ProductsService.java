package vn.ducthe.service;

import vn.ducthe.dto.request.ProductCreateRequest;
import vn.ducthe.dto.request.ProductUpdateRequest;
import vn.ducthe.dto.response.ProductDTO;

import java.util.List;

public interface ProductsService {
    List<ProductDTO> getProductsTopDeal();
    List<ProductDTO> getProductsByCategory(String slug);
    Long createProduct(ProductCreateRequest productCreateRequest);
    void updateProduct(ProductUpdateRequest productUpdateRequest);
}
