package jwtdemo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(	name = "users",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "username")
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String username;


	@NotBlank
	@Size(max = 120)
	private String password;


	public User() {
	}

	public User( String username, String password) {
		this.username = username;
		this.password = password;
	}

}
