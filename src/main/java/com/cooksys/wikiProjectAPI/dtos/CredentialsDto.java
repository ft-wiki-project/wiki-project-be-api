package com.cooksys.wikiProjectAPI.dtos;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CredentialsDto {
  @NotNull(message = "Username cannot be null")
  private String username;
  
  @NotNull(message = "Password cannot be null")
  private String password;
}
