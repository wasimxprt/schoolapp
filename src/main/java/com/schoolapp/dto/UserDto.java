package com.schoolapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "UserDTO schema definition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;

	@Schema(description="User first name")
	@NotEmpty(message = "Please entr first name")
	private String firstName;

	@Schema(description="User last name")
	@NotEmpty(message = "Please enter last name")
	private String lastName;

	@Schema(description="User email address")
	@NotEmpty(message = "Please enter email")
	@Email(message = "Please a enter valid email")
	private String email;	

}
