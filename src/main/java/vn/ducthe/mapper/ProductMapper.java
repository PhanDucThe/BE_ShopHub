package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.request.CreateProductRequest;
import vn.ducthe.dto.response.ProductDTO;
import vn.ducthe.exception.ResourceNotFoundException;
import vn.ducthe.model.ImageEntity;
import vn.ducthe.model.ProductEntity;
import vn.ducthe.model.VariantEntity;
import vn.ducthe.repository.*;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ImageRepository imageRepository;
    private final PriceMapper priceMapper;
    private final ReviewMapper reviewMapper;

    public ProductEntity createToEntity(CreateProductRequest createProduct) {
        ProductEntity product = new ProductEntity();
        product.setName(createProduct.getName());
        product.setSlug(createProduct.getSlug());
        product.setDescription(createProduct.getDescription());

        product.setCategoryEntity(categoryRepository.findById(createProduct.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Danh mục tồn tại!")));
        product.setBrandEntity(brandRepository.findById(createProduct.getBrandId()).orElseThrow(() -> new ResourceNotFoundException("Thương hiệu không tồn tại!")));
        return product;
    }

    public ProductDTO toDTO(VariantEntity variantEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(variantEntity.getId());
        productDTO.setProductName(variantEntity.getVariantName());
        productDTO.setProductSlug(variantEntity.getSlug());
        ImageEntity imagesEntity = imageRepository.findByVariantEntity_IdAndSortOrder(variantEntity.getId(), 1);
        productDTO.setProductImagesMain(imagesEntity.getImageUrl());
        productDTO.setBrandName(variantEntity.getProductEntity().getBrandEntity().getName());
        productDTO.setCategoryName(variantEntity.getProductEntity().getCategoryEntity().getName());
        productDTO.setPrice(priceMapper.toPriceDTO(variantEntity));
        productDTO.setReviews(reviewMapper.toReviewDTO(variantEntity.getReviewEntities()));
        productDTO.setSold(variantEntity.getSold());
        productDTO.setStatus("active");
        productDTO.setStock(variantEntity.getStock());

        // url product detail.
        String tempUrl = variantEntity.getProductEntity().getSlug();

        productDTO.setProductSlug(tempUrl);
        return productDTO;
    }

}
