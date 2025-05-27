package com.cooksys.wikiProjectAPI.services;

import com.cooksys.wikiProjectAPI.dtos.TeamRequestDto;
import com.cooksys.wikiProjectAPI.dtos.TeamResponseDto;

public interface TeamService {

  TeamResponseDto createTeam(TeamRequestDto teamRequestDto);

}
