package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.request.ProductCreateRequest;
import vn.ducthe.dto.response.ProductDTO;
import vn.ducthe.dto.response.ReviewDTO;
import vn.ducthe.entity.*;
import vn.ducthe.repository.ImagesRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ImagesRepository imagesRepository;
    private final PriceMapper priceMapper;
    private final ReviewMapper reviewMapper;


    public ProductDTO toDto(VariantsEntity variantsEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(variantsEntity.getId());
        productDTO.setProductName(variantsEntity.getVariantName());
        ImagesEntity imagesEntity = imagesRepository.findByVariantsEntity_IdAndSortOrder(variantsEntity.getId(), 1);
        productDTO.setProductImagesMain(imagesEntity.getImageUrl());
        productDTO.setBrandName(variantsEntity.getProductsEntity().getBrandsEntity().getName());
        productDTO.setCategoryName(variantsEntity.getProductsEntity().getCategoriesEntity().getName());
        productDTO.setPrice(priceMapper.toPriceDTO(variantsEntity));
        productDTO.setReviews(reviewMapper.toReviewDTO(variantsEntity.getReviewsEntities()));
        productDTO.setSold(variantsEntity.getSold());
        return productDTO;
    }

    public ProductsEntity toEntity(ProductCreateRequest productCreateRequest) {
        // Đã sử dụng cascade rồi.
        // Tách các thông tin riêng biệt từng phần ra.
        // B1. Product information base, sau đó save vào để lưu lại có ID.
        // Và mình đã biết là Shop, Category, Brand đã có rồi nên không cần phải truy vấn DB nữa
        ProductsEntity productsEntity = new ProductsEntity();
        // Base Information
        ShopsEntity shops = new ShopsEntity();
        shops.setId(productCreateRequest.getBasicInfo().getShopId());
        CategoriesEntity categories = new CategoriesEntity();
        categories.setId(productCreateRequest.getBasicInfo().getCategoryId());
        BrandsEntity brands = new BrandsEntity();
        brands.setId(productCreateRequest.getBasicInfo().getBrandId());
        productsEntity.setShopsEntity(shops);
        productsEntity.setCategoriesEntity(categories);
        productsEntity.setBrandsEntity(brands);
        productsEntity.setName(productCreateRequest.getBasicInfo().getName());
        productsEntity.setSlug(productCreateRequest.getBasicInfo().getSlug());
        productsEntity.setDescription(productCreateRequest.getBasicInfo().getDescription());
        return  productsEntity;
    }
}
