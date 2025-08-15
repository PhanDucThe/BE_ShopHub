package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.entity.AttributeOptionsEntity;
import vn.ducthe.entity.VariantAttributeOptionsEntity;
import vn.ducthe.entity.VariantsEntity;
import vn.ducthe.repository.AttributeOptionsRepository;
import vn.ducthe.repository.VariantAttributeOptionsRepository;

@Component
@RequiredArgsConstructor
public class VariantAttributeOptionMapper {

    private final AttributeOptionsRepository attributeOptionsRepository;
    private final VariantAttributeOptionsRepository variantAttributeOptionsRepository;

    public VariantAttributeOptionsEntity toEntityCreate(VariantsEntity variantsEntity, Long attributeId) {
        VariantAttributeOptionsEntity variantAttributeOptionsEntity = new VariantAttributeOptionsEntity();
        variantAttributeOptionsEntity.setVariantsEntity(variantsEntity);
        AttributeOptionsEntity attributeOptionsEntity = attributeOptionsRepository.findById(attributeId).get();
        variantAttributeOptionsEntity.setAttributeOptionsEntity(attributeOptionsEntity);
        return  variantAttributeOptionsEntity;
    }

}
