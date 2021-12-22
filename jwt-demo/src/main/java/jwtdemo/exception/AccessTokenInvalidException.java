package jwtdemo.exception;

public class AccessTokenInvalidException extends RuntimeException {
    public AccessTokenInvalidException() {
        super("Invalid access-token");
    }

}
