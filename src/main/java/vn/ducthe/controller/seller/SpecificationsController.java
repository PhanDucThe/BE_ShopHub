package vn.ducthe.controller.seller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ducthe.common.ApiResponse;
import vn.ducthe.service.SpecificationsService;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class SpecificationsController {

    private final SpecificationsService specificationsService;

    @GetMapping(value = "/v1/specifications")
    public ApiResponse<?>  getSpecifications() {
        return new ApiResponse<>(HttpStatus.OK.value(), "success", specificationsService.getSpecifications());
    }
}
