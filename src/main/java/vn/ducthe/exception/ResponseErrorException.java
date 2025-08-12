package vn.ducthe.exception;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseErrorException {
    private Date timestamp;
    private int status;
    private String path;
    private String error;
    private String message;
}
