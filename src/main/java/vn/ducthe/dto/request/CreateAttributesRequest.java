package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateAttributesRequest {
    private String attributeName;
    private Long attributeId;
    private String attributeValue;
    private Boolean isNew;
    private Long optionId;
}
