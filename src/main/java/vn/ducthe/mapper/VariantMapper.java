package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.request.ProductVariantRequest;
import vn.ducthe.entity.ImagesEntity;
import vn.ducthe.entity.ProductsEntity;
import vn.ducthe.entity.VariantAttributeOptionsEntity;
import vn.ducthe.entity.VariantsEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VariantMapper {

    private final ImageMapper imageMapper;
    private final VariantAttributeOptionMapper  variantAttributeOptionMapper;

    public VariantsEntity toEntity(ProductVariantRequest productVariantRequest, ProductsEntity productsEntity) {
        VariantsEntity variantsEntity = new VariantsEntity();
        variantsEntity.setSkuCode(productVariantRequest.getSkuCode());
        variantsEntity.setVariantName(productVariantRequest.getVariantName());
        variantsEntity.setOriginalPrice(productVariantRequest.getOriginalPrice());
        variantsEntity.setSalePrice(productVariantRequest.getSalePrice());
        variantsEntity.setStock(productVariantRequest.getStock());
        variantsEntity.setSold(0); // Default is Zero

        // Phan Xu li Anh
        List<ImagesEntity> images = productVariantRequest.getImages().stream().map(img -> {
            return imageMapper.toEntity(img,variantsEntity);
        }).toList();
        variantsEntity.setImagesEntities(images);

        // Xu li Cac Attribute_Option
        List<VariantAttributeOptionsEntity> variantAttributeOptionsEntities = productVariantRequest.getAttributeOptionIds().stream().map(attribute -> {
            return variantAttributeOptionMapper.toEntity(variantsEntity, attribute);
        }).toList();
        variantsEntity.setProductsEntity(productsEntity);
        variantsEntity.setVariantAttributeOptionsEntities(variantAttributeOptionsEntities);
        return variantsEntity;
    }
}
