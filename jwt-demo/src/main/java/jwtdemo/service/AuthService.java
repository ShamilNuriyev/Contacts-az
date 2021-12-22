package jwtdemo.service;

import jwtdemo.payload.request.LoginRequest;
import jwtdemo.payload.request.SignupRequest;
import jwtdemo.payload.request.TokenRefreshRequest;
import jwtdemo.payload.response.JwtResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthService {

    ResponseEntity<JwtResponse> signIn(LoginRequest loginRequest);

    ResponseEntity<?> refreshToken(TokenRefreshRequest request);

    ResponseEntity<?> signUp(SignupRequest signUpRequest);


     boolean validateAccessToken(String token);
}
