package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.request.CreateProductRequest;
import vn.ducthe.dto.response.ProductDTO;
import vn.ducthe.exception.ResourceNotFoundException;
import vn.ducthe.model.AttributeOptionEntity;
import vn.ducthe.model.ImageEntity;
import vn.ducthe.model.ProductEntity;
import vn.ducthe.model.VariantEntity;
import vn.ducthe.repository.*;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ImageRepository imageRepository;
    private final PriceMapper priceMapper;
    private final ReviewMapper reviewMapper;
    private final AttributeOptionRepository attributeOptionRepository;

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
        productDTO.setImage(variantEntity.getImage());
        productDTO.setBrandName(variantEntity.getProductEntity().getBrandEntity().getName());
        productDTO.setCategoryName(variantEntity.getProductEntity().getCategoryEntity().getParent().getName());
        productDTO.setPrice(priceMapper.toPriceDTO(variantEntity));
        productDTO.setReviews(reviewMapper.toReviewDTO(variantEntity.getReviewEntities()));
        productDTO.setSold(variantEntity.getSold());
        productDTO.setStatus("active");
        productDTO.setStock(variantEntity.getStock());

        // url product detail.
        String tempUrl = variantEntity.getProductEntity().getSlug();

        // Bay giờ mình phải lấy option ra.
        String signature = variantEntity.getOptionSignature();
        // Tách từng option ra đã.
        String[] options = signature.split("_");
        Map<String, String> optionSelected = new HashMap<>();
        for (String option : options) {
            AttributeOptionEntity attributeOptionEntity = attributeOptionRepository.findById(Long.parseLong(option))
                    .orElseThrow(() -> new ResourceNotFoundException("Option not found"));
            optionSelected.put(attributeOptionEntity.getAttributeEntity().getNameEn(), attributeOptionEntity.getValue());
        }
        productDTO.setOption(optionSelected);

        productDTO.setProductSlug(tempUrl);
        return productDTO;
    }

}
