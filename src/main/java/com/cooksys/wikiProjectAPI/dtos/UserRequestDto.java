package com.cooksys.wikiProjectAPI.dtos;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
  private CredentialsDto credentials;
  private ProfileDto profile;
  private String status;
  private boolean admin;
  private boolean active;
}
