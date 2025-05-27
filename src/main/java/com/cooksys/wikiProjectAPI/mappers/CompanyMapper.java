package com.cooksys.wikiProjectAPI.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.wikiProjectAPI.dtos.CompanyRequestDto;
import com.cooksys.wikiProjectAPI.dtos.CompanyResponseDto;
import com.cooksys.wikiProjectAPI.entities.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
  Company requestDtoToEntity(CompanyRequestDto companyRequestDto);

  CompanyResponseDto entityToDto(Company company);
  
  List<CompanyResponseDto> entitiesToDtos(List<Company> companies);
}
