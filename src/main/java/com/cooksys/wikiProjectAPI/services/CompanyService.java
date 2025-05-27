package com.cooksys.wikiProjectAPI.services;

import java.util.List;

import com.cooksys.wikiProjectAPI.dtos.CompanyResponseDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;

public interface CompanyService {

	List<CompanyResponseDto> getCompaines(Long userId);

	List<UserResponseDto> getAllUsers(Long companyId);

}
