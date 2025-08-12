package vn.ducthe.mapper;

import org.springframework.stereotype.Component;
import vn.ducthe.dto.request.VariantImage;
import vn.ducthe.dto.response.ImageDTO;
import vn.ducthe.entity.ImagesEntity;
import vn.ducthe.entity.VariantsEntity;

@Component
public class ImageMapper {

    public ImagesEntity toEntity(VariantImage variantImage, VariantsEntity variantsEntity) {
        ImagesEntity imagesEntity = new ImagesEntity();
        imagesEntity.setImageUrl(variantImage.getUrl());
        imagesEntity.setSortOrder(variantImage.getSortOrder());
        imagesEntity.setImageAlt(variantImage.getAlt());
        imagesEntity.setVariantsEntity(variantsEntity); // Set nguoc lai khoa ngoai quan trong.
        return  imagesEntity;
    }
}
