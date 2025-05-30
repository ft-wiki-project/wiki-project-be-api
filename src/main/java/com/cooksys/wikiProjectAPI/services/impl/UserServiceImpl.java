package com.cooksys.wikiProjectAPI.services.impl;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.cooksys.wikiProjectAPI.dtos.CredentialsDto;
import com.cooksys.wikiProjectAPI.dtos.UserRequestDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;
import com.cooksys.wikiProjectAPI.entities.User;
import com.cooksys.wikiProjectAPI.entities.Company;
import com.cooksys.wikiProjectAPI.exceptions.BadRequestException;
import com.cooksys.wikiProjectAPI.exceptions.NotFoundException;
import com.cooksys.wikiProjectAPI.mappers.UserMapper;
import com.cooksys.wikiProjectAPI.repositories.CompanyRepository;
import com.cooksys.wikiProjectAPI.repositories.UserRepository;
import com.cooksys.wikiProjectAPI.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final CompanyRepository companyRepository;

	@Override
	public UserResponseDto login(CredentialsDto credentialsDto) {
		if (credentialsDto == null || credentialsDto.getUsername() == null || credentialsDto.getPassword() == null) {
			throw new BadRequestException("Invalid login request");
		}

		Optional<User> userToBeLoggedIn = userRepository.findByCredentialsUsernameAndCredentialsPassword(credentialsDto.getUsername(), credentialsDto.getPassword());

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

		if (userRepository.findByCredentialsUsername(request.getCredentials().getUsername()).isPresent()) {
			throw new BadRequestException("Username already exists");
		}

		if (request.getCompanyId() == null) {
			throw new BadRequestException("Company ID cannot be null");
		}

		Optional<Company> companyOptional = companyRepository.findById(request.getCompanyId());

		if (companyOptional.isEmpty()) {
			throw new NotFoundException("Company with ID " + request.getCompanyId() + " does not exist");
		}

		User user = userMapper.requestDtoToEntity(request);

		user.getCompanies().add(companyOptional.get());
		user.setActive(true);
		user.setStatus("PENDING");

		return userMapper.entityToDto(userRepository.saveAndFlush(user));
	}

	@Override
	public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {
		if (userId == null) {
			throw new BadRequestException("UserId does not exsist");
		}
		
		Optional<User> userToFind = userRepository.findById(userId);
		if (userToFind.isEmpty()) {
			throw new NotFoundException("User was not found");
		}
		
		User user = userToFind.get();
		
    if (userRequestDto.getProfile() != null) {
      user.getProfile().setFirst(userRequestDto.getProfile().getFirst());
      user.getProfile().setLast(userRequestDto.getProfile().getLast());
      user.getProfile().setEmail(userRequestDto.getProfile().getEmail());
      user.getProfile().setPhone(userRequestDto.getProfile().getPhone());
      user.getCredentials().setUsername(userRequestDto.getCredentials().getUsername());
      user.setAdmin(userRequestDto.isAdmin());
      if (userRequestDto.getCredentials().getPassword() != null) {
        user.getCredentials().setPassword(userRequestDto.getCredentials().getPassword());
      }
    } else {
      user.setStatus("JOINED");
    }
    
		
		return userMapper.entityToDto(userRepository.saveAndFlush(user));
	}
}
