package com.cooksys.wikiProjectAPI.services;

import java.util.List;

import com.cooksys.wikiProjectAPI.dtos.ProjectRequestDto;
import com.cooksys.wikiProjectAPI.dtos.ProjectResponseDto;

public interface ProjectService {

  ProjectResponseDto createProject(ProjectRequestDto projectRequestDto);

  List<ProjectResponseDto> getProjectsByTeamId(Long teamId);

}
