package vn.ducthe.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.common.Util;
import vn.ducthe.model.*;
import vn.ducthe.repository.VariantRepository;
import vn.ducthe.service.ProductOptionsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductOptionDtoFactory {
    private final VariantRepository variantRepository;
    private final Util util;

    public Map<String, Object> createSingleAttributeOption(ProductEntity product, AttributeOptionEntity attributeOption,
                                                           AttributeEntity attributeEntity, List<CategoryAttributeEntity> categoryAttributeEntities) {

        Map<String, String> tempOption = createTempOptionMap(attributeEntity, attributeOption);
        String optionSignature = util.getSignatureFromOptions(tempOption, categoryAttributeEntities);
        Optional<VariantEntity> variantEntity = findVariant(product.getId(), optionSignature);

        return createOptionDto(attributeOption, variantEntity);
    }

    public Map<String, Object> createTwoAttributeOption(ProductEntity product, AttributeOptionEntity primary,
                                                        AttributeOptionEntity secondary, AttributeEntity primaryAttribute, AttributeEntity secondaryAttribute,
                                                        List<CategoryAttributeEntity> categoryAttributeEntities) {

        Map<String, String> tempOption = createTempOptionMap(primaryAttribute, primary, secondaryAttribute, secondary);
        String optionSignature = util.getSignatureFromOptions(tempOption, categoryAttributeEntities);
        Optional<VariantEntity> variantEntity = findVariant(product.getId(), optionSignature);

        return createOptionDto(secondary, variantEntity);
    }

    private Map<String, String> createTempOptionMap(AttributeEntity attributeEntity, AttributeOptionEntity attributeOption) {
        Map<String, String> tempOption = new HashMap<>();
        tempOption.put(util.toSlugify(attributeEntity.getNameEn()), util.toSlugify(attributeOption.getValue()));
        return tempOption;
    }

    private Map<String, String> createTempOptionMap(AttributeEntity primaryAttribute, AttributeOptionEntity primary,
                                                    AttributeEntity secondaryAttribute, AttributeOptionEntity secondary) {
        Map<String, String> tempOption = new HashMap<>();
        tempOption.put(util.toSlugify(primaryAttribute.getNameEn()), util.toSlugify(primary.getValue()));
        tempOption.put(util.toSlugify(secondaryAttribute.getNameEn()), util.toSlugify(secondary.getValue()));
        return tempOption;
    }

    private Optional<VariantEntity> findVariant(Long productId, String optionSignature) {
        return variantRepository.findByProductEntity_IdAndOptionSignature(productId, optionSignature);
    }

    private Map<String, Object> createOptionDto(AttributeOptionEntity attributeOption, Optional<VariantEntity> variantEntity) {
        Map<String, Object> optionDto = new HashMap<>();
        optionDto.put("value", attributeOption.getValue());
        optionDto.put("slug", attributeOption.getSlug());
        optionDto.put("available", isVariantAvailable(variantEntity));

        if (variantEntity.isPresent()) {
            VariantEntity variant = variantEntity.get();
            optionDto.put("originalPrice", variant.getOriginalPrice());
            optionDto.put("salePrice", variant.getSalePrice());
            optionDto.put("image",  variant.getImage());
        } else {
            optionDto.put("originalPrice", 0.0);
            optionDto.put("salePrice", 0.0);
            optionDto.put("image", "");
        }

        return optionDto;
    }

    private boolean isVariantAvailable(Optional<VariantEntity> variantEntity) {
        return variantEntity.isPresent() && variantEntity.get().getStock() > 0;
    }

}
