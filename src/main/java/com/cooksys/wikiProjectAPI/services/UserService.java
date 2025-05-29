package com.cooksys.wikiProjectAPI.services;

import com.cooksys.wikiProjectAPI.dtos.CredentialsDto;
import com.cooksys.wikiProjectAPI.dtos.UserRequestDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;
import com.cooksys.wikiProjectAPI.entities.User;
import com.cooksys.wikiProjectAPI.mappers.UserMapper;
import com.cooksys.wikiProjectAPI.repositories.UserRepository;

public interface UserService {

	UserResponseDto login(CredentialsDto credentialsDto);

	UserResponseDto createUser(UserRequestDto request);

	UserResponseDto updateUser(Long userId);
}