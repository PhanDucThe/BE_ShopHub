package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.request.ProductVariantCreatRequest;
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

    public VariantsEntity toEntity(ProductVariantCreatRequest productVariantRequest, ProductsEntity productsEntity) {
        VariantsEntity variantsEntity; // variantsEntity = null
        if (productVariantRequest.getVariantId() != null) {
            variantsEntity = variantsRepository.findById(productVariantRequest.getVariantId()).orElse(null);
        } else {
            variantsEntity = new  VariantsEntity();
        }

        variantsEntity.setProductsEntity(productsEntity);

        variantsEntity.setSkuCode(productVariantRequest.getSkuCode());
        variantsEntity.setVariantName(productVariantRequest.getVariantName());
        variantsEntity.setOriginalPrice(productVariantRequest.getOriginalPrice());
        variantsEntity.setSalePrice(productVariantRequest.getSalePrice());
        variantsEntity.setStock(productVariantRequest.getStock());
        variantsEntity.setSold(0); // Default is Zero


        // Phan Xu li Anh
        List<ImagesEntity> newImages  = productVariantRequest.getImages().stream().map(img -> imageMapper.toEntity(img, variantsEntity)).toList();
        newImages.forEach(image -> image.setVariantsEntity(variantsEntity));
        variantsEntity.setImagesEntities(newImages);



        // Xu li Cac Attribute_Option
        List<VariantAttributeOptionsEntity> newAttributeOptions  = productVariantRequest.getAttributeOptionIds().stream().map(attributeId  -> variantAttributeOptionMapper.toEntity(variantsEntity, attributeId)).toList();
        newAttributeOptions.forEach(option -> option.setVariantsEntity(variantsEntity));

        // Thay thế collection
        variantsEntity.setVariantAttributeOptionsEntities(newAttributeOptions);
        return variantsEntity;
    }
}
