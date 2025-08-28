package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.common.Util;
import vn.ducthe.dto.request.CreateAttributesRequest;
import vn.ducthe.dto.request.CreateProductVariantRequest;
import vn.ducthe.dto.response.ImageDTO;
import vn.ducthe.dto.response.VariantDTO;
import vn.ducthe.exception.ResourceNotFoundException;
import vn.ducthe.model.*;
import vn.ducthe.repository.AttributeOptionRepository;
import vn.ducthe.repository.AttributeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VariantMapper {

    private final AttributeOptionRepository attributeOptionRepository;
    private final AttributeRepository  attributeRepository;
    private final Util util;

    public VariantEntity createToDto(CreateProductVariantRequest request, ProductEntity product, Map<String, AttributeOptionEntity> attributeOptionCache) {
        VariantEntity dto = new VariantEntity();
        dto.setProductEntity(product);
        dto.setVariantName(request.getVariantName());
        dto.setOriginalPrice(request.getOriginalPrice());
        dto.setSalePrice(request.getSalePrice());
        dto.setStock(request.getStock());
        dto.setSold(0);

        // Thu Thap Attribute_Option de sau cung luu lai
        List<Long> optionIds  = new ArrayList<>();

        List<VariantAttributeOptionEntity> variantAttributeOptions = request.getAttributes().stream().map(attribute -> {
            AttributeOptionEntity attributeOption = getOrCreateAttributeOption(attribute, attributeOptionCache);
            optionIds.add(attributeOption.getId()); // Co di da
            VariantAttributeOptionEntity variantAttributeOption = new VariantAttributeOptionEntity();
            variantAttributeOption.setVariantEntity(dto);
            variantAttributeOption.setAttributeOptionEntity(attributeOption);
            return variantAttributeOption;
        }).toList();
        dto.setVariantAttributeOptionEntities(variantAttributeOptions);

        // Generate option_signature
        Collections.sort(optionIds);
        String optionSignature = optionIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("_"));
        dto.setOptionSignature(optionSignature);

        return dto;
    }

    private AttributeOptionEntity getOrCreateAttributeOption (CreateAttributesRequest  createAttributesRequest, Map<String, AttributeOptionEntity> attributeOptionCache) {
        String cacheKey = createAttributesRequest.getAttributeId() + "_" + createAttributesRequest.getAttributeValue(); // 1_Den

        // Check cache truoc.
        if (attributeOptionCache.containsKey(cacheKey)) {
            return attributeOptionCache.get(cacheKey);
        }

        AttributeOptionEntity attributeOption;
        if (Boolean.TRUE.equals(createAttributesRequest.getIsNew())) {
            // Mac du la moi nhung ma van can thuan kiem tra xong data da
            Optional<AttributeOptionEntity> existing = attributeOptionRepository.findByAttributeEntity_IdAndValue(createAttributesRequest.getAttributeId(), createAttributesRequest.getAttributeValue());
            if (existing.isPresent()) {
                attributeOption = existing.get();
            } else {
                // Tao moi
                AttributeEntity attribute = attributeRepository.findById(createAttributesRequest.getAttributeId())
                        .orElseThrow(() -> new ResourceNotFoundException("Attribute not found: " + createAttributesRequest.getAttributeId()));
                attributeOption = new AttributeOptionEntity();
                attributeOption.setAttributeEntity(attribute);
                attributeOption.setValue(createAttributesRequest.getAttributeValue());
                attributeOptionRepository.save(attributeOption);
            }
        } else {
            // Su dung cai co san
            attributeOption = attributeOptionRepository.findById(createAttributesRequest.getOptionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Option not found: " + createAttributesRequest.getOptionId()));
        }
        attributeOptionCache.put(cacheKey, attributeOption);
        return attributeOption;
    }

    public VariantDTO toVariantDTO(VariantEntity variantEntity, Map<String, String> params) {
        VariantDTO dto = new VariantDTO();
        dto.setVariantId(variantEntity.getId());
        dto.setOriginalPrice(variantEntity.getOriginalPrice());
        dto.setSalePrice(variantEntity.getSalePrice());
        dto.setStock(variantEntity.getStock());
        dto.setSold(variantEntity.getSold());
        dto.setImage(variantEntity.getImage());

        // Option selected
        Map<String, String> optionSelected = new HashMap<>();
        variantEntity.getProductEntity().getCategoryEntity().getCategoryAttributeEntities()
                .forEach(categoryAttributeEntity -> {
                    AttributeEntity attributeEntity = categoryAttributeEntity.getAttributeEntity();
                    String slug = params.get(util.toSlugify(attributeEntity.getNameEn()));
                    String value = util.getValueFromSlug(attributeEntity.getId(), slug);
                    optionSelected.put(attributeEntity.getNameEn(), value);
                });
        dto.setSelectOption(optionSelected);

        return dto;
    }
}
