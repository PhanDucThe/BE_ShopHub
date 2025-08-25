package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAttributeRequest {
    private Long attributeId;
    private String attributeName;
    private String attributeValue;
    private Boolean isNew;
    private Long optionId;
}
