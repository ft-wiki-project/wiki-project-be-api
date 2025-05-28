package com.cooksys.wikiProjectAPI.services;

import java.util.List;

import com.cooksys.wikiProjectAPI.dtos.TeamRequestDto;
import com.cooksys.wikiProjectAPI.dtos.TeamResponseDto;

public interface TeamService {

  TeamResponseDto createTeam(TeamRequestDto teamRequestDto);
  
  List<TeamResponseDto> getTeams(Long companyId);

  List<TeamResponseDto> getTeamByUser(Long userId);

}
