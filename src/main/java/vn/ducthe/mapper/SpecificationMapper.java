package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.SpecificationDTO;
import vn.ducthe.exception.ResourceNotFoundException;
import vn.ducthe.model.ProductSpecificationEntity;
import vn.ducthe.model.SpecificationEntity;
import vn.ducthe.repository.SpecificationRepository;

@Component
@RequiredArgsConstructor
public class SpecificationMapper {

    private final SpecificationRepository specificationRepository;

    public SpecificationDTO toDTO(ProductSpecificationEntity productSpecificationEntity) {
        SpecificationDTO specificationDTO = new SpecificationDTO();
        SpecificationEntity specificationEntity = specificationRepository.findById(productSpecificationEntity.getSpecificationEntity().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Specification not found"));
        specificationDTO.setName(specificationEntity.getName());
        specificationDTO.setValue(productSpecificationEntity.getValue());
        return specificationDTO;
    }
}
