package com.cooksys.wikiProjectAPI.services;

import com.cooksys.wikiProjectAPI.dtos.CredentialsDto;
import com.cooksys.wikiProjectAPI.dtos.UserRequestDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;

public interface UserService {

	UserResponseDto login(CredentialsDto credentialsDto);

	UserResponseDto createUser(UserRequestDto request);

	UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto);
}