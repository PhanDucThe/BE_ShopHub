package vn.ducthe.mapper;

import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.PriceDTO;
import vn.ducthe.model.VariantEntity;

@Component
public class PriceMapper {
    public PriceDTO toPriceDTO(VariantEntity variantEntity) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setOriginalPrice(variantEntity.getOriginalPrice());
        priceDTO.setCurrency("đ");
        if (variantEntity.getSalePrice() == null) {
            priceDTO.setSalePrice(variantEntity.getOriginalPrice());
        } else {
            priceDTO.setSalePrice(variantEntity.getSalePrice());
        }
        priceDTO.setPercentDiscount((int) Math.floor((priceDTO.getOriginalPrice() -  priceDTO.getSalePrice()) / priceDTO.getOriginalPrice() * 100));
        priceDTO.setDiscount(priceDTO.getOriginalPrice() - priceDTO.getSalePrice());
        return  priceDTO;
    }
}
