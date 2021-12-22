package jwtdemo.payload.response;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class JwtResponse {
	private String accessToken;
	private String refreshToken;
	private int expires_on;
	private String type = "Bearer";
	private Long id;
	private String username;
;

	public JwtResponse(String accessToken,String refreshToken,int expires_on, Long id, String username){
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expires_on = expires_on;
		this.id = id;
		this.username = username;
	}


}
