package com.cooksys.wikiProjectAPI.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.wikiProjectAPI.dtos.CompanyResponseDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;
import com.cooksys.wikiProjectAPI.services.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {
	
	private final CompanyService companyService;
	
	@GetMapping("/{userId}")
	public List<CompanyResponseDto> getCompanies(@PathVariable Long userId) {
		return companyService.getCompaines(userId);
	}
	
	@GetMapping("/{companyId}/users")
	public List<UserResponseDto> getAllUsers(@PathVariable Long companyId) {
		return companyService.getAllUsers(companyId);
	}

}
