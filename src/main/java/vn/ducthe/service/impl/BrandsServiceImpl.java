package vn.ducthe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.ducthe.dto.response.BrandDTO;
import vn.ducthe.entity.BrandsEntity;
import vn.ducthe.repository.BrandsRepository;
import vn.ducthe.service.BrandsService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandsServiceImpl implements BrandsService {

    private final BrandsRepository brandsRepository;

    @Override
    public List<BrandDTO> getAllBrands() {
        List<BrandDTO> brands = new ArrayList<>();
        List<BrandsEntity> brandsEntities = brandsRepository.findAll();
        for (BrandsEntity brandsEntity : brandsEntities) {
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setBrandId(brandsEntity.getId());
            brandDTO.setBrandName(brandsEntity.getName());
            brandDTO.setSlug(brandsEntity.getSlug());
            brands.add(brandDTO);
        }
        return brands;
    }
}
