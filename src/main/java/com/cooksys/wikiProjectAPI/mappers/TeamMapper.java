package com.cooksys.wikiProjectAPI.mappers;
import java.util.List;
import org.mapstruct.Mapper;
import com.cooksys.wikiProjectAPI.dtos.TeamRequestDto;
import com.cooksys.wikiProjectAPI.dtos.TeamResponseDto;
import com.cooksys.wikiProjectAPI.entities.Team;

@Mapper(componentModel = "spring")
public interface TeamMapper {
  Team requestDtoToEntity(TeamRequestDto teamRequestDto);

  TeamResponseDto entityToDto(Team team);
  
  List<TeamResponseDto> entitiesToDtos(List<Team> teams);
}
