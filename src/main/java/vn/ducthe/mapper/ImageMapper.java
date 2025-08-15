package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.request.VariantImageCreateRequest;
import vn.ducthe.entity.ImagesEntity;
import vn.ducthe.entity.VariantsEntity;
import vn.ducthe.repository.ImagesRepository;

@Component
@RequiredArgsConstructor
public class ImageMapper {

    private final ImagesRepository  imagesRepository;

    public ImagesEntity toEntity(VariantImageCreateRequest variantImage, VariantsEntity variantsEntity) {
        ImagesEntity imagesEntity;
        if (variantImage.getImageId() != null) {
            imagesEntity = imagesRepository.findById(variantImage.getImageId()).orElse(null);
        } else {
            imagesEntity = new ImagesEntity();
        }

        imagesEntity.setVariantsEntity(variantsEntity); // Set nguoc lai khoa ngoai quan trong.
        imagesEntity.setImageUrl(variantImage.getUrl());
        imagesEntity.setSortOrder(variantImage.getSortOrder());
        imagesEntity.setImageAlt(variantImage.getAlt());
        return  imagesEntity;
    }
}
