package vn.ducthe.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceDTO {
    private Double salePrice ;
    private Double originalPrice;
    private String currency;
    private Double discount;
    private int percentDiscount;
}
