package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.BrandDTO;
import vn.ducthe.dto.response.CategoryDTO;
import vn.ducthe.dto.response.ImageDTO;
import vn.ducthe.dto.response.ProductDetailDTO;
import vn.ducthe.model.BrandEntity;
import vn.ducthe.model.CategoryEntity;
import vn.ducthe.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDetailMapper {


    public ProductDetailDTO toDTO(ProductEntity product) {
        // Basic information
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setProductId(product.getId());
        productDetailDTO.setProductName(product.getName());
        productDetailDTO.setSlug(product.getSlug());
        productDetailDTO.setDescription(product.getDescription());
        CategoryEntity category = product.getCategoryEntity();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getId());
        categoryDTO.setCategoryName(category.getName());
        productDetailDTO.setCategory(categoryDTO);

        BrandEntity brand = product.getBrandEntity();
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setBrandId(brand.getId());
        brandDTO.setBrandName(brand.getName());
        productDetailDTO.setBrand(brandDTO);

        // Images
        List<ImageDTO> imageDTOList = product.getImageEntities().stream().map(img -> {
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setId(img.getId());
            imageDTO.setUrl(img.getImageUrl());
            imageDTO.setAlt(img.getImageAlt());
            imageDTO.setSortOrder(img.getSortOrder());
            imageDTO.setIsPrimary(img.getIsPrimary());
            return  imageDTO;
        }).toList();
        productDetailDTO.setImages(imageDTOList);

        return productDetailDTO;
    }
}
