package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.ProductDTO;
import vn.ducthe.entity.*;
import vn.ducthe.repository.ImagesRepository;

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
        productDTO.setStatus(variantsEntity.getStatus());
        productDTO.setStock(variantsEntity.getStock());
        productDTO.setSkuCode(variantsEntity.getSkuCode());
        return productDTO;
    }

}
