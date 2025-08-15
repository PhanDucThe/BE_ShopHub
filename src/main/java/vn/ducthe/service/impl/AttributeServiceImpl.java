package vn.ducthe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.ducthe.dto.response.AttributeDTO;
import vn.ducthe.dto.response.AttributeOptionDTO;
import vn.ducthe.entity.AttributeOptionsEntity;
import vn.ducthe.entity.AttributesEntity;
import vn.ducthe.repository.AttributesRepository;
import vn.ducthe.service.AttributeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final AttributesRepository attributesRepository;

    @Override
    public List<AttributeOptionDTO> getAttributes() {
        List<AttributeOptionDTO> attributesDTO = new ArrayList<>();
        // Get all Attribute
        List<AttributesEntity>  attributes = attributesRepository.findAll();
        // For Look
        for (AttributesEntity attribute : attributes){
            AttributeOptionDTO attributeDTO = new AttributeOptionDTO();
            attributeDTO.setId(attribute.getId());
            attributeDTO.setName(attribute.getName());
           List<AttributeDTO>  attributeDTOs = new ArrayList<>();
           for (AttributeOptionsEntity item : attribute.getAttributesOptionsEntities()) {
               AttributeDTO  itemDTO = new AttributeDTO();
               itemDTO.setId(item.getId());
               itemDTO.setName(item.getValue());
               attributeDTOs.add(itemDTO);
           }
            attributeDTO.setAttributes(attributeDTOs);
            attributesDTO.add(attributeDTO);
        }
        return attributesDTO;
    }
}
