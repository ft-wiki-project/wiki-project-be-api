package com.cooksys.wikiProjectAPI.controllers;
import org.springframework.web.bind.annotation.PatchMapping;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.wikiProjectAPI.dtos.ProjectRequestDto;
import com.cooksys.wikiProjectAPI.dtos.ProjectResponseDto;
import com.cooksys.wikiProjectAPI.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

  private final ProjectService projectService;
  
  @PostMapping()
  public ProjectResponseDto createProject(@RequestBody ProjectRequestDto projectRequestDto) {
    return projectService.createProject(projectRequestDto);
  }
  
  @PatchMapping("/{projectId}")
  public ProjectResponseDto editProject(@RequestBody ProjectRequestDto projectRequestDto, @PathVariable Long projectId) {
	  return projectService.editProject(projectRequestDto, projectId);
  }
  
  @GetMapping("/{teamId}")
  public List<ProjectResponseDto> getProjectsByTeamId(@PathVariable Long teamId) {
    return projectService.getProjectsByTeamId(teamId);
  }

}
