package vn.ducthe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalException {
    // Bat tat ca cac exception.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorException handleExceptionValidation(MethodArgumentNotValidException e, WebRequest request) {
        int firstIndex = e.getMessage().lastIndexOf('[') + 1;
        int secondIndex = e.getMessage().lastIndexOf(']') - 1;
        return ResponseErrorException.builder()
                .timestamp(new Date())
                .path(request.getDescription(false).replace("uri=", ""))
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage().substring(firstIndex, secondIndex))
                .build();
    }
}
