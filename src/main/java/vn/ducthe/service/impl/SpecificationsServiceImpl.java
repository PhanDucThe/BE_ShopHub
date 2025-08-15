package vn.ducthe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.ducthe.dto.response.AttributeDTO;
import vn.ducthe.entity.SpecificationsEntity;
import vn.ducthe.repository.SpecificationsRepository;
import vn.ducthe.service.SpecificationsService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecificationsServiceImpl implements SpecificationsService {

    private final SpecificationsRepository specificationsRepository;

    @Override
    public List<AttributeDTO> getSpecifications() {
        List<AttributeDTO> attributeDTOS = new ArrayList<>();
        List<SpecificationsEntity> specifications =  specificationsRepository.findAll();
        for (SpecificationsEntity specificationsEntity : specifications) {
            AttributeDTO attributeDTO = new AttributeDTO();
            attributeDTO.setId(specificationsEntity.getId());
            attributeDTO.setName(specificationsEntity.getName());
            attributeDTOS.add(attributeDTO);
        }
        return attributeDTOS;
    }
}
