package com.cooksys.wikiProjectAPI.services;

import com.cooksys.wikiProjectAPI.dtos.CredentialsDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;

public interface UserService {

  UserResponseDto login(CredentialsDto credentialsDto);

}
