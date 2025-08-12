package vn.ducthe.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Builder
public class ApiResponse<T> implements Serializable {
    private int status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    // Apply for method PUT and DELTE and PATCH
    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // Apply for method GET and POST
    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
