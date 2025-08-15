package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.request.CreateVariantRequest;
import vn.ducthe.entity.ImagesEntity;
import vn.ducthe.entity.ProductsEntity;
import vn.ducthe.entity.VariantAttributeOptionsEntity;
import vn.ducthe.entity.VariantsEntity;
import vn.ducthe.repository.VariantsRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VariantMapper {

    private final ImageMapper imageMapper;
    private final VariantAttributeOptionMapper  variantAttributeOptionMapper;
    private final VariantsRepository  variantsRepository;

    public VariantsEntity toEntityCreate(CreateVariantRequest createVariantRequest, ProductsEntity productsEntity) {
        VariantsEntity variant = new VariantsEntity();

        variant.setSkuCode(createVariantRequest.getSkuCode());
        variant.setVariantName(createVariantRequest.getVariantName());
        variant.setOriginalPrice(createVariantRequest.getOriginalPrice());
        variant.setSalePrice(createVariantRequest.getSalePrice());
        variant.setStock(createVariantRequest.getStock());
        variant.setStatus("active");
        variant.setSold(0); // Default is Zero

        // Phan Xu li Anh
        List<ImagesEntity> newImages  = createVariantRequest.getImages().stream().map(img -> imageMapper.toEntityCreate(img, variant)).toList();
        variant.setImagesEntities(newImages);

        // Xu li Cac Attribute_Option
        List<VariantAttributeOptionsEntity> newAttributeOptions = createVariantRequest.getAttributeOptionIds().stream().map(attributeId -> variantAttributeOptionMapper.toEntityCreate(variant,  attributeId)).toList();
        variant.setVariantAttributeOptionsEntities(newAttributeOptions);

        variant.setProductsEntity(productsEntity);
        return variant;
    }


}
