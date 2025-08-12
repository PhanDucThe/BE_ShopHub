package vn.ducthe.common;


import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ApiError implements Serializable {
    private Date timestamp;
    private int status;
    private String path;
    private String error;
    private String message;
}
