package jwtdemo.controllers;

import javax.validation.Valid;

import jwtdemo.payload.request.LoginRequest;
import jwtdemo.payload.request.SignupRequest;
import jwtdemo.payload.request.TokenRefreshRequest;
import jwtdemo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:63342",allowCredentials = "true")
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/signin")
		public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {
			return authService.signIn(loginRequest);
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
		return authService.refreshToken(request);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return authService.signUp(signUpRequest);
	}

	@PostMapping("/validate/access-token")
	public void validateAccessToken(@RequestHeader("Access-Token") String token){
		authService.validateAccessToken(token);
	}
}
