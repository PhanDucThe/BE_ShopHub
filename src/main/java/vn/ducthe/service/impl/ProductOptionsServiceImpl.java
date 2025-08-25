package vn.ducthe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.ducthe.common.Util;
import vn.ducthe.factory.ProductOptionDtoFactory;
import vn.ducthe.model.AttributeEntity;
import vn.ducthe.model.AttributeOptionEntity;
import vn.ducthe.model.CategoryAttributeEntity;
import vn.ducthe.model.ProductEntity;
import vn.ducthe.repository.VariantRepository;
import vn.ducthe.service.ProductOptionsService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductOptionsServiceImpl implements ProductOptionsService {

    private final VariantRepository variantRepository;
    private final Util util;
    private final ProductOptionDtoFactory optionDtoFactory;

    @Override
    public Map<String, Object> buildAvailableOptions(ProductEntity product, List<CategoryAttributeEntity> categoryAttributeEntities) {
        Map<String, Object> availableOptions = new TreeMap<>();

        if (categoryAttributeEntities.isEmpty()) {
            return availableOptions;
        }

        if (categoryAttributeEntities.size() == 1) {
            buildSingleAttributeOptions(product, categoryAttributeEntities.get(0), availableOptions);
        } else if (categoryAttributeEntities.size() == 2) {
            buildTwoAttributeOptions(product, categoryAttributeEntities, availableOptions);
        } else {
            // Handle more than 2 attributes if needed in the future
            buildMultipleAttributeOptions(product, categoryAttributeEntities, availableOptions);
        }

        return availableOptions;
    }

    private void buildSingleAttributeOptions(ProductEntity product, CategoryAttributeEntity categoryAttribute, Map<String, Object> availableOptions) {
        AttributeEntity attributeEntity = categoryAttribute.getAttributeEntity();
        List<Map<String, Object>> attributeOptions = new ArrayList<>();

        for (AttributeOptionEntity attributeOption : attributeEntity.getAttributeOptions()) {
            Map<String, Object> optionDto = optionDtoFactory.createSingleAttributeOption(
                    product, attributeOption, attributeEntity, List.of(categoryAttribute)
            );
            attributeOptions.add(optionDto);
        }

        availableOptions.put(attributeEntity.getNameEn(), attributeOptions);
    }

    private void buildTwoAttributeOptions(ProductEntity product, List<CategoryAttributeEntity> categoryAttributeEntities, Map<String, Object> availableOptions) {
        AttributeEntity primaryAttribute = categoryAttributeEntities.get(0).getAttributeEntity();
        AttributeEntity secondaryAttribute = categoryAttributeEntities.get(1).getAttributeEntity();

        List<Map<String, Object>> primariesList = new ArrayList<>();

        for (AttributeOptionEntity primary : primaryAttribute.getAttributeOptions()) {
            Map<String, Object> optionPrimary = buildPrimaryOption(
                    product, primary, primaryAttribute, secondaryAttribute, categoryAttributeEntities
            );

            if (!optionPrimary.isEmpty()) {
                primariesList.add(optionPrimary);
            }
        }

        availableOptions.put(primaryAttribute.getNameEn(), primariesList);
    }

    private Map<String, Object> buildPrimaryOption(ProductEntity product, AttributeOptionEntity primary,
                                                   AttributeEntity primaryAttribute, AttributeEntity secondaryAttribute,
                                                   List<CategoryAttributeEntity> categoryAttributeEntities) {

        Map<String, Object> optionPrimary = new HashMap<>();
        List<Map<String, Object>> secondaryOptions = new ArrayList<>();
        boolean hasAvailableSecondary = false;

        for (AttributeOptionEntity secondary : secondaryAttribute.getAttributeOptions()) {
            Map<String, Object> secondaryOptionDto = optionDtoFactory.createTwoAttributeOption(
                    product, primary, secondary, primaryAttribute, secondaryAttribute, categoryAttributeEntities
            );

            if ((Boolean) secondaryOptionDto.get("available")) {
                hasAvailableSecondary = true;
            }

            secondaryOptions.add(secondaryOptionDto);
        }

        if (hasAvailableSecondary) {
            optionPrimary.put("value", primary.getValue());
            optionPrimary.put("slug", primary.getSlug());
            optionPrimary.put("available", true);
            optionPrimary.put(secondaryAttribute.getNameEn(), secondaryOptions);
        }

        return optionPrimary;
    }

    private void buildMultipleAttributeOptions(ProductEntity product, List<CategoryAttributeEntity> categoryAttributeEntities, Map<String, Object> availableOptions) {
        // Implementation for handling more than 2 attributes
        // This can be implemented later if needed
        throw new UnsupportedOperationException("Multiple attributes (>2) not yet supported");
    }
}
