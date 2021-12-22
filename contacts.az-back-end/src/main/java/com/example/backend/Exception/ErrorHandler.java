package com.example.backend.Exception;

import com.example.backend.dao.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleStudentNotFoundException(EmployeeNotFoundException ex,
                                                                 WebRequest webRequest) {
        return handleExceptionInternal(ex,
                new ErrorDto("employee.not-found", ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                webRequest);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex,
                                                         WebRequest webRequest) {
        return handleExceptionInternal(ex,
                new ErrorDto("employee.unexpected-error", ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return handleExceptionInternal(ex,
                ErrorDto.builder().code(ex.getMessage())
                        .message(errors.toString())
                        .build(),
                headers,HttpStatus.BAD_REQUEST,request);


    }
}
