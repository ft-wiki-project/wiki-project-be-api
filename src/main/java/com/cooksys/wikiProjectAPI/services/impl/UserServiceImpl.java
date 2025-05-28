package com.cooksys.wikiProjectAPI.services.impl;
import java.util.Optional;

import org.apache.coyote.http11.upgrade.UpgradeServletInputStream;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.stereotype.Service;

import com.cooksys.wikiProjectAPI.dtos.CredentialsDto;
import com.cooksys.wikiProjectAPI.dtos.UserRequestDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;
import com.cooksys.wikiProjectAPI.entities.User;
import com.cooksys.wikiProjectAPI.exceptions.BadRequestException;
import com.cooksys.wikiProjectAPI.mappers.UserMapper;
import com.cooksys.wikiProjectAPI.repositories.UserRepository;
import com.cooksys.wikiProjectAPI.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  
  @Override
  public UserResponseDto login(CredentialsDto credentialsDto) {
    if (credentialsDto == null || credentialsDto.getUsername() == null || credentialsDto.getPassword() == null) {
      throw new BadRequestException("Invalid login request");
    }

    Optional<User> userToBeLoggedIn = userRepository.findByCredentialsUsernameAndCredentialsPassword(
      credentialsDto.getUsername(),
      credentialsDto.getPassword()
    );
    
    if (userToBeLoggedIn.isEmpty()) {
      throw new BadRequestException("Invalid username or password");
    }

    User user = userToBeLoggedIn.get();
    return userMapper.entityToDto(user);
  }

@Override
public UserResponseDto createUser(UserRequestDto request) {
    if (request == null || request.getProfile() == null || request.getCredentials() == null) {
        throw new BadRequestException("User must have credentials and profile information");
    }

    String email = request.getProfile().getEmail();

    if (email == null || email.isBlank()) {
        throw new BadRequestException("Email is required");
    }

    if (userRepository.findByProfileEmail(email).isPresent()) {
        throw new BadRequestException("Email already exists");
    }

	if(userRepository.findByCredentialsUsername(request.getCredentials().getUsername()).isPresent()) {
		throw new BadRequestException("Username already exists");
	}

    User user = userMapper.requestDtoToEntity(request);

    user.setActive(true);
    user.setStatus("PENDING");

    return userMapper.entityToDto(userRepository.saveAndFlush(user));
}





}
