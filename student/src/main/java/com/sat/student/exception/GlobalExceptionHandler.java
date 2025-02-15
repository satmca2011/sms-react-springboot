package com.sat.student.exception;

import com.sat.student.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest){
        return new ResponseEntity<>(getErrorResponseDto(exception, webRequest,HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        return new ResponseEntity<>(getErrorResponseDto(exception, webRequest,HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String ,String> validationErrors = new HashMap<>();
       List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
       allErrors.forEach((error) -> {
           String fieldName = ((FieldError)error).getField();
           String errorMessage = error.getDefaultMessage();
           validationErrors.put(fieldName, errorMessage);
       });
       return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    private static ErrorResponseDto getErrorResponseDto(Exception exception, WebRequest webRequest, HttpStatus httpStatus) {
        return new  ErrorResponseDto(
                 webRequest.getDescription(false),
                 httpStatus,
                 exception.getMessage(),
                 LocalDateTime.now());
    }
}
