package com.cooksys.wikiProjectAPI.services;

import java.util.List;

import com.cooksys.wikiProjectAPI.dtos.CompanyResponseDto;

public interface CompanyService {

	List<CompanyResponseDto> getCompaines(Long userId);

}
