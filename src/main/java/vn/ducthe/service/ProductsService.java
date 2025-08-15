package vn.ducthe.service;

import vn.ducthe.dto.request.CreateProductRequest;
import vn.ducthe.dto.response.ProductDTO;

import java.util.List;

public interface ProductsService {
    List<ProductDTO> getProductsTopDeal();
    List<ProductDTO> getProductsByCategory(String slug);
    Long createProduct(CreateProductRequest createProductRequest);
    List<ProductDTO> getAllProducts(); // Mai mot se sua sau khi co dieu kien
    List<ProductDTO> getNewProducts();
}
