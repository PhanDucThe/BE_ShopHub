package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.request.CreateImageRequest;
import vn.ducthe.entity.ImagesEntity;
import vn.ducthe.entity.VariantsEntity;
import vn.ducthe.repository.ImagesRepository;

@Component
@RequiredArgsConstructor
public class ImageMapper {

    private final ImagesRepository  imagesRepository;

    public ImagesEntity toEntityCreate(CreateImageRequest variantImage, VariantsEntity variantsEntity) {
        ImagesEntity imagesEntity = new ImagesEntity();
        imagesEntity.setImageUrl(variantImage.getUrl());
        imagesEntity.setSortOrder(variantImage.getSortOrder());
        imagesEntity.setImageAlt(variantImage.getAlt());
        imagesEntity.setVariantsEntity(variantsEntity); // Set nguoc lai khoa ngoai quan trong.
        return  imagesEntity;
    }


}
