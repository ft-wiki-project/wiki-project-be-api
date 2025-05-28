package com.cooksys.wikiProjectAPI.services.impl;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.wikiProjectAPI.dtos.ProjectRequestDto;
import com.cooksys.wikiProjectAPI.dtos.ProjectResponseDto;
import com.cooksys.wikiProjectAPI.entities.Project;
import com.cooksys.wikiProjectAPI.entities.Team;
import com.cooksys.wikiProjectAPI.exceptions.BadRequestException;
import com.cooksys.wikiProjectAPI.mappers.ProjectMapper;
import com.cooksys.wikiProjectAPI.repositories.ProjectRepository;
import com.cooksys.wikiProjectAPI.repositories.TeamRepository;
import com.cooksys.wikiProjectAPI.services.ProjectService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;
  private final TeamRepository teamRepository;

  @Override
  public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto) {
    if (projectRequestDto == null) {
      throw new BadRequestException("Project request cannot be null");
    }
    if (projectRequestDto.getName() == null || projectRequestDto.getName().isEmpty()) {
      throw new BadRequestException("Project name cannot be null or empty");
    }
    if (projectRequestDto.getDescription() == null || projectRequestDto.getDescription().isEmpty()) {
      throw new BadRequestException("Project description cannot be null or empty");
    }
    if (projectRequestDto.getTeamId() == null) {
      throw new BadRequestException("Project team cannot be null or empty");
    }

    Optional<Team> teamOptional = teamRepository.findById(projectRequestDto.getTeamId());
    if (teamOptional.isEmpty()) {
      throw new BadRequestException("Team with ID " + projectRequestDto.getTeamId() + " does not exist");
    }

    Project projectToSave = projectMapper.requestDtoToEntity(projectRequestDto);

    projectToSave.setTeam(teamOptional.get());
    projectToSave.setName(projectRequestDto.getName());
    projectToSave.setDescription(projectRequestDto.getDescription());

    return projectMapper.entityToDto(projectRepository.saveAndFlush(projectToSave));
  }

}
