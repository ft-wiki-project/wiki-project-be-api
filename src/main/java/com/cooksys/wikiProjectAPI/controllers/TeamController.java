package com.cooksys.wikiProjectAPI.controllers;
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
public class TeamController {
  private final TeamService teamService;

  @PostMapping()
  public TeamResponseDto createTeam(@RequestBody TeamRequestDto teamRequestDto) {
    return teamService.createTeam(teamRequestDto);
  }

}
