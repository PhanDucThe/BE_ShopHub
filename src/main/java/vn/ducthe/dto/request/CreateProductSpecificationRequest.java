package vn.ducthe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductSpecificationRequest {
    private String name;
    private String value;
    private Boolean isNew;
}
