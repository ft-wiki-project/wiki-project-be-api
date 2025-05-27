package com.cooksys.wikiProjectAPI.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProfileDto {
  private String firstName;
  private String lastName;

  @NotNull(message = "Email cannot be null")
  @Email(message = "Please enter a valid email address")
  private String email;

  private String phone;
}
