package com.pfairplay.api.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Order(1)
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> handleException(CustomException e, HttpServletRequest httpServletRequest) {
        List<CustomError> customErrors = new ArrayList<>();
        customErrors.add(CustomError.fromCustomErrorEnum(e.getCustomErrorEnum()));
        return ResponseEntity.status(e.getHttpStatus()).body(new CustomErrorResponse(e.getHttpStatus().value(), CustomError.fromCustomErrorEnum(e.getCustomErrorEnum())));
    }
}
