package jwtdemo.exception;

public class AccessTokenExpiredException extends RuntimeException {
    public AccessTokenExpiredException() {
        super("expired access-token");
    }
}
