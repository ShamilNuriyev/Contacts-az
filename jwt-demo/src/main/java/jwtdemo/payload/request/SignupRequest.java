package jwtdemo.payload.request;


import javax.validation.constraints.*;

public class SignupRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

 
    public String getUsername() {
        return username;
    }

 
    public String getPassword() {
        return password;
    }

}
