package com.cooksys.wikiProjectAPI.services.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.wikiProjectAPI.dtos.CompanyResponseDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;
import com.cooksys.wikiProjectAPI.entities.Company;
import com.cooksys.wikiProjectAPI.entities.User;
import com.cooksys.wikiProjectAPI.exceptions.BadRequestException;
import com.cooksys.wikiProjectAPI.exceptions.NotFoundException;
import com.cooksys.wikiProjectAPI.mappers.CompanyMapper;
import com.cooksys.wikiProjectAPI.mappers.UserMapper;
import com.cooksys.wikiProjectAPI.repositories.CompanyRepository;
import com.cooksys.wikiProjectAPI.repositories.UserRepository;
import com.cooksys.wikiProjectAPI.services.CompanyService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
	
	private final CompanyMapper companyMapper;
	private final CompanyRepository companyRepository;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Override
	public List<CompanyResponseDto> getCompaines(Long userId) {
		if (userId == null) {
			throw new BadRequestException("User does not exsist");
		}
		
		Optional<User> userToFind = userRepository.findById(userId);
		
		if (userToFind.isEmpty()) {
			throw new NotFoundException("User was not found");
		}
		
		User user = userToFind.get();
		
		List<Company> companyList = user.getCompanies();
		
		return companyMapper.entitiesToDtos(companyList);
		
	}

	@Override
	public List<UserResponseDto> getAllUsers(Long companyId) {
		if (companyId == null) {
			throw new BadRequestException("Company does not exsist");
		}
		
		Optional<Company> companyToFind = companyRepository.findById(companyId);
		
		List<User> usersInCompany = companyToFind.get().getUsers();
		
		return userMapper.entitiesToDtos(usersInCompany);
	}

}
