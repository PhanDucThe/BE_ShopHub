package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.entity.AttributeOptionsEntity;
import vn.ducthe.entity.VariantAttributeOptionsEntity;
import vn.ducthe.entity.VariantsEntity;
import vn.ducthe.repository.AttributeOptionsRepository;

@Component
@RequiredArgsConstructor
public class VariantAttributeOptionMapper {

    private final AttributeOptionsRepository attributeOptionsRepository;

    public VariantAttributeOptionsEntity toEntity(VariantsEntity variantsEntity, Long attributeId) {
        VariantAttributeOptionsEntity variantAttributeOptionsEntity = new VariantAttributeOptionsEntity();
        variantAttributeOptionsEntity.setVariantsEntity(variantsEntity);
        AttributeOptionsEntity attributeOptionsEntity = attributeOptionsRepository.findById(attributeId).get();
        variantAttributeOptionsEntity.setAttributeOptionsEntity(attributeOptionsEntity);
        return  variantAttributeOptionsEntity;
    }
}
