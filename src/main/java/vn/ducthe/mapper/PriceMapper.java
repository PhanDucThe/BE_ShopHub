package vn.ducthe.mapper;

import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.PriceDTO;
import vn.ducthe.entity.PromotionsEntity;
import vn.ducthe.entity.VariantsEntity;

@Component
public class PriceMapper {
    public PriceDTO toPriceDTO(VariantsEntity variantsEntity) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setOriginalPrice(variantsEntity.getOriginalPrice());
        priceDTO.setCurrency("đ");
        if (variantsEntity.getSalePrice() == null) {
            priceDTO.setSalePrice(variantsEntity.getOriginalPrice());
        } else {
            priceDTO.setSalePrice(variantsEntity.getSalePrice());
        }
        priceDTO.setPercentDiscount((int) Math.floor((priceDTO.getOriginalPrice() -  priceDTO.getSalePrice()) / priceDTO.getOriginalPrice() * 100));
        priceDTO.setDiscount(priceDTO.getOriginalPrice() - priceDTO.getSalePrice());
        return  priceDTO;
    }
}
