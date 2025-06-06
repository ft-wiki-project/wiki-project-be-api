package com.cooksys.wikiProjectAPI.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.wikiProjectAPI.dtos.TeamRequestDto;
import com.cooksys.wikiProjectAPI.dtos.TeamResponseDto;
import com.cooksys.wikiProjectAPI.services.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TeamController {
  private final TeamService teamService;

  @PostMapping()
  public TeamResponseDto createTeam(@RequestBody TeamRequestDto teamRequestDto) {
    return teamService.createTeam(teamRequestDto);
  }
  
  @GetMapping("/{companyId}")
  public List<TeamResponseDto> getTeams(@PathVariable Long companyId) {
	  return teamService.getTeams(companyId);
  }
  
  @GetMapping("/{userId}/users")
  public List<TeamResponseDto> getTeamsByUser(@PathVariable Long userId) {
	  return teamService.getTeamByUser(userId);
  }
  
  @PatchMapping("/{teamId}")
  public TeamResponseDto editTeam(@PathVariable Long teamId, @RequestBody TeamRequestDto teamRequestDto) {
	  return teamService.editTeam(teamId, teamRequestDto);
  }
  
  @DeleteMapping("/{teamId}")
  public TeamResponseDto deleteTeam(@PathVariable Long teamId) {
	  return teamService.deleteTeam(teamId);
  }

} 
