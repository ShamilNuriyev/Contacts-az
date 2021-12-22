package jwtdemo.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:63342",allowCredentials = "true")
@RequestMapping("/home")
public class TestController {
    @GetMapping("/user")
    public String userAccess() {
        return "success";
    }
}
