package api.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(2)
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleException(MethodArgumentNotValidException e) {

        CustomErrorResponse customErrorResponse =
                new CustomErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        CustomError.fromCustomErrorEnum(CustomErrorEnum.BAD_REQUEST_ERROR));

        e.getBindingResult().getFieldErrors().forEach(error -> {
            customErrorResponse.getError().getErrors().put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception e, HttpServletRequest httpServletRequest) {
        System.out.println("Method : " + httpServletRequest.getMethod());
        System.out.println("Path : " + httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new CustomErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                CustomError.fromCustomErrorEnum(CustomErrorEnum.INTERNAL_SERVER_ERROR))
                );
    }

}
