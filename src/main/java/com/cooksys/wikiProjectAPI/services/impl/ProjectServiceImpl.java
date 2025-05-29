package com.cooksys.wikiProjectAPI.services.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.wikiProjectAPI.dtos.ProjectRequestDto;
import com.cooksys.wikiProjectAPI.dtos.ProjectResponseDto;
import com.cooksys.wikiProjectAPI.entities.Project;
import com.cooksys.wikiProjectAPI.entities.Team;
import com.cooksys.wikiProjectAPI.exceptions.BadRequestException;
import com.cooksys.wikiProjectAPI.exceptions.NotFoundException;
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

	@Override
	public ProjectResponseDto editProject(ProjectRequestDto projectRequestDto, Long projectId) {
		
		if (projectId == null) {
			throw new BadRequestException("Invalid Project Id");
		}
		
		if (projectRequestDto.getName() == null && projectRequestDto.getDescription() == null) {
			throw new BadRequestException("Please provide a name or a description");

		}
		
		Optional<Project> project = projectRepository.findById(projectId);
		if (project == null) {
			throw new BadRequestException("Project Does not exsist");
		}
		
		if (projectRequestDto.getName() != null) {
			project.get().setName(projectRequestDto.getName());
			projectRepository.saveAndFlush(project.get());
		}
		
		if (projectRequestDto.getDescription() != null) {
			project.get().setDescription(projectRequestDto.getDescription());
			projectRepository.saveAndFlush(project.get());
		}
		
		if (projectRequestDto.getName() != null && projectRequestDto.getDescription() != null) {
			project.get().setName(projectRequestDto.getName());
			project.get().setDescription(projectRequestDto.getDescription());
			projectRepository.saveAndFlush(project.get());
		}

		if (projectRequestDto.isActive() != project.get().isActive()) {
			project.get().setActive(projectRequestDto.isActive());
			projectRepository.saveAndFlush(project.get());
		}

		
		
		return projectMapper.entityToDto(project.get());

		
	}

	@Override
	public List<ProjectResponseDto> getProjectsByTeamId(Long teamId) {
		if (teamId == null) {
      throw new BadRequestException("Team ID cannot be null");
    }

    Optional<Team> teamOptional = teamRepository.findById(teamId);
    if (teamOptional.isEmpty()) {
      throw new BadRequestException("Team with ID " + teamId + " does not exist");
    }

    List<Project> projects = projectRepository.findByTeam(teamOptional.get());
    if (projects.isEmpty()) {
      throw new NotFoundException("No projects found for team with ID " + teamId);
    }
    
    return projectMapper.entitiesToDtos(projects);
	}

}
