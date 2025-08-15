package vn.ducthe.controller.seller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ducthe.common.ApiResponse;
import vn.ducthe.service.AttributeService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AttributeOptionController {

    private final AttributeService attributeService;

    @GetMapping(value = "/v1/attribute-options")
    public ApiResponse<?>  getAttributeOptions() {
        return new ApiResponse<>(HttpStatus.OK.value(), "OK", attributeService.getAttributes());
    }
}
