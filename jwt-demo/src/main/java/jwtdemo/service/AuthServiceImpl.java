package jwtdemo.service;

import jwtdemo.exception.TokenRefreshException;
import jwtdemo.models.RefreshToken;
import jwtdemo.models.User;
import jwtdemo.payload.request.LoginRequest;
import jwtdemo.payload.request.SignupRequest;
import jwtdemo.payload.request.TokenRefreshRequest;
import jwtdemo.payload.response.JwtResponse;
import jwtdemo.payload.response.MessageResponse;
import jwtdemo.payload.response.TokenRefreshResponse;
import jwtdemo.repository.UserRepository;
import jwtdemo.security.jwt.AuthTokenFilter;
import jwtdemo.security.jwt.JwtUtils;
import jwtdemo.security.services.RefreshTokenService;
import jwtdemo.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    JwtUtils jwtUtils;
    RefreshTokenService refreshTokenService;
    AuthTokenFilter authTokenFilter;
    AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, JwtUtils jwtUtils, RefreshTokenService refreshTokenService, AuthTokenFilter authTokenFilter, AuthenticationManager authenticationManager, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.authTokenFilter = authTokenFilter;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
    }

    PasswordEncoder encoder;


    @Override
    public ResponseEntity<JwtResponse> signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), jwtUtils.getJwtExpirationMs(), userDetails.getId(),
                userDetails.getUsername()));
    }

    @Override
    public ResponseEntity<?> refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @Override
    public ResponseEntity<?> signUp(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public boolean validateAccessToken(String token) {
        if (!jwtUtils.validateJwtToken(token)) {
            //TODO 401 and 403
            throw new RuntimeException("test");
        }
        return true;
    }
}

