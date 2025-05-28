package com.cooksys.wikiProjectAPI.services;

import com.cooksys.wikiProjectAPI.dtos.ProjectRequestDto;
import com.cooksys.wikiProjectAPI.dtos.ProjectResponseDto;

public interface ProjectService {

  ProjectResponseDto createProject(ProjectRequestDto projectRequestDto);

ProjectResponseDto editProject(ProjectRequestDto projectRequestDto, Long projectId);

}
