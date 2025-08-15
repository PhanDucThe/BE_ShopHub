package vn.ducthe.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.ducthe.dto.request.ProductSpecificationRequest;
import vn.ducthe.entity.ProductSpecificationsEntity;
import vn.ducthe.entity.ProductsEntity;
import vn.ducthe.entity.SpecificationsEntity;
import vn.ducthe.repository.ProductSpecificationsRepository;
import vn.ducthe.repository.SpecificationsRepository;

@Component
@RequiredArgsConstructor
public class ProductSpecificationMapper {

    private final SpecificationsRepository specificationsRepository;
    private final ProductSpecificationsRepository  productSpecificationsRepository;

    public ProductSpecificationsEntity toEntity(ProductSpecificationRequest productSpecificationRequest, ProductsEntity productsEntity) {
        ProductSpecificationsEntity productSpecificationsEntity;
        if (productSpecificationRequest.getSpecId() != null) {
            productSpecificationsEntity =  productSpecificationsRepository.findById(productSpecificationRequest.getSpecId()).get();
        } else {
            productSpecificationsEntity = new  ProductSpecificationsEntity();
        }

        productSpecificationsEntity.setProductsEntity(productsEntity);
        SpecificationsEntity specificationsEntity = specificationsRepository.findByName(productSpecificationRequest.getKey()).get();
        productSpecificationsEntity.setSpecificationsEntity(specificationsEntity);
        productSpecificationsEntity.setValue(productSpecificationRequest.getValue());
        return productSpecificationsEntity;
    }

}
