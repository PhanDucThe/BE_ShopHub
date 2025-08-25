package vn.ducthe.service;

import vn.ducthe.dto.request.CreateProductRequest;
import vn.ducthe.dto.response.ProductDTO;
import vn.ducthe.dto.response.ProductDetailDTO;

import java.util.List;
import java.util.Map;

public interface ProductsService {
    Long  createProduct(CreateProductRequest createProductRequest);
    ProductDetailDTO getProductDetails(String slug, Map<String,String> params);
    List<ProductDTO> getProductsTopDeal();
    List<ProductDTO> getNewProducts();
    List<ProductDTO> getProductsByCategory(String slug);
}
