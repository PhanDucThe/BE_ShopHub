package vn.ducthe.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class AttributeOptionDTO {
    private Long id;
    private String name;
    List<AttributeDTO> attributes =  new ArrayList<>();
}
