package jwtdemo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessTokenExpiredException.class)
    public ResponseEntity<Object> handleAccessTokenExpiredException(AccessTokenExpiredException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex,
                new RuntimeException( "expired-access-token", ex),
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED,
                webRequest);
    }

    @ExceptionHandler(AccessTokenInvalidException.class)
    public ResponseEntity<Object> handleAccessTokenInvalidException(AccessTokenInvalidException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex,
                new RuntimeException( "invalid-access-token", ex),
                new HttpHeaders(),
                HttpStatus.FORBIDDEN,
                webRequest);
    }

}