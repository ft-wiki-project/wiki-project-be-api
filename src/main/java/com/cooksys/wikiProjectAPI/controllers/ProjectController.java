package com.cooksys.wikiProjectAPI.controllers;
import java.util.List;

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
public class ProjectController {

  private final ProjectService projectService;
  
  @PostMapping()
  public ProjectResponseDto createProject(@RequestBody ProjectRequestDto projectRequestDto) {
    return projectService.createProject(projectRequestDto);
  }

  @GetMapping("/{teamId}")
  public List<ProjectResponseDto> getProjectsByTeamId(@PathVariable Long teamId) {
    return projectService.getProjectsByTeamId(teamId);
  }

}
