package com.fackstoreintract.fackstoreinteract.controllerAdvice;

import com.fackstoreintract.fackstoreinteract.Dtos.ErrorResponseDto;
import com.fackstoreintract.fackstoreinteract.exceptions.ProductNotPresentException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponseDto> handlerArithmeticException(){
        return new ResponseEntity<>(new ErrorResponseDto("Divide by zero"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ErrorResponseDto> handleInvalidProduct(){
        return new ResponseEntity<>(new ErrorResponseDto("Invalid Product from goble"),HttpStatus.NOT_FOUND);
    }
}
