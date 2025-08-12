package vn.ducthe.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryBaseDTO {
    private String name;
    private String slug;
    private String image;
    private String description;
}
