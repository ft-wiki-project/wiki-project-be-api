package com.cooksys.wikiProjectAPI.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.wikiProjectAPI.dtos.ProjectRequestDto;
import com.cooksys.wikiProjectAPI.dtos.ProjectResponseDto;
import com.cooksys.wikiProjectAPI.entities.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
  Project requestDtoToEntity(ProjectRequestDto projectRequestDto);

  ProjectResponseDto entityToDto(Project project);
  
  List<ProjectResponseDto> entitiesToDtos(List<Project> projects);
}
