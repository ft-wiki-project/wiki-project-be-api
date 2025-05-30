package com.cooksys.wikiProjectAPI.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cooksys.wikiProjectAPI.dtos.TeamRequestDto;
import com.cooksys.wikiProjectAPI.dtos.TeamResponseDto;
import com.cooksys.wikiProjectAPI.entities.Company;
import com.cooksys.wikiProjectAPI.entities.Team;
import com.cooksys.wikiProjectAPI.entities.User;
import com.cooksys.wikiProjectAPI.exceptions.BadRequestException;
import com.cooksys.wikiProjectAPI.exceptions.NotFoundException;
import com.cooksys.wikiProjectAPI.mappers.TeamMapper;
import com.cooksys.wikiProjectAPI.mappers.UserMapper;
import com.cooksys.wikiProjectAPI.repositories.CompanyRepository;
import com.cooksys.wikiProjectAPI.repositories.TeamRepository;
import com.cooksys.wikiProjectAPI.repositories.UserRepository;
import com.cooksys.wikiProjectAPI.services.TeamService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
	private final TeamRepository teamRepository;
	private final TeamMapper teamMapper;
	private final CompanyRepository companyRepository;
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public TeamResponseDto createTeam(TeamRequestDto teamRequestDto) {
		if (teamRequestDto == null) {
			throw new BadRequestException("Team data cannot be null");
		}
		if (teamRequestDto.getName() == null || teamRequestDto.getName().isEmpty()) {
			throw new BadRequestException("Team name cannot be null or empty");
		}
		if (teamRequestDto.getCompanyId() == null) {
			throw new BadRequestException("Company ID cannot be null");
		}
		if (teamRequestDto.getUserIds() == null || teamRequestDto.getUserIds().isEmpty()) {
			throw new BadRequestException("Team must have at least one user");
		}
		if (teamRequestDto.getDescription() == null || teamRequestDto.getDescription().isEmpty()) {
			throw new BadRequestException("Team description cannot be null or empty");
		}

		Optional<Company> companyOptional = companyRepository.findById(teamRequestDto.getCompanyId());
		if (companyOptional.isEmpty()) {
			throw new BadRequestException("Company with ID " + teamRequestDto.getCompanyId() + " does not exist");
		}

		Team team = teamMapper.requestDtoToEntity(teamRequestDto);

		team.setCompany(companyOptional.get());
		team.setDescription(teamRequestDto.getDescription());
		team.setName(teamRequestDto.getName());
		teamRepository.saveAndFlush(team);

		for (Long userId : teamRequestDto.getUserIds()) {
			Optional<User> userOptional = userRepository.findById(userId);
			if (userOptional.isEmpty()) {
				throw new BadRequestException("User with id " + userId + " does not exist");
			}
			team.getUsers().add(userOptional.get());
			userOptional.get().getTeams().add(team);
			userRepository.saveAndFlush(userOptional.get());
		}

		return teamMapper.entityToDto(teamRepository.saveAndFlush(team));
	}

	@Override
	public List<TeamResponseDto> getTeams(Long companyId) {

		if (companyId == null) {
			throw new BadRequestException("Company does not exist");
		}

		Optional<Company> companyToFind = companyRepository.findById(companyId);
		if (companyToFind.isEmpty()) {
			throw new NotFoundException("Company not found");
		}
		

		List<Team> teamsInCompany = teamRepository.findAllByCompanyAndDeletedFalse(companyToFind.get());

		return teamMapper.entitiesToDtos(teamsInCompany);

	}

	@Override
	public List<TeamResponseDto> getTeamByUser(Long userId) {
		if (userId == null) {
			throw new BadRequestException("User does not exist");
		}
		
		Optional<User> userToFind = userRepository.findById(userId);
		if (userToFind.isEmpty()) {
			throw new NotFoundException("User not found");
		}
		
		List<Team> teams = new ArrayList<>();
		List<Team> allUserTeams = userToFind.get().getTeams();
		for (Team team : allUserTeams) {
			if (!team.isDeleted()) {
				teams.add(team);
			}
		}
		
		return teamMapper.entitiesToDtos(teams);
	}

	@Override
	public TeamResponseDto editTeam(Long teamId, TeamRequestDto teamRequestDto) {
		if (teamId == null) {
	        throw new BadRequestException("Team ID must not be null");
	    }

	    Optional<Team> teamToFind = teamRepository.findById(teamId);
	    if (teamToFind.isEmpty()) {
	        throw new NotFoundException("Team not found");
	    }

	    Team team = teamToFind.get();
	    team.setName(teamRequestDto.getName());
	    
	    team.setDescription(teamRequestDto.getDescription());

	    for (Long userId : teamRequestDto.getUserIds()) {
	    	
	    	Optional<User> userOptional = userRepository.findById(userId);
			if (userOptional.isEmpty()) {
				throw new BadRequestException("User with id " + userId + " does not exist");
			}
			if (!team.getUsers().contains(userOptional.get())) {
				team.getUsers().add(userOptional.get());
				userOptional.get().getTeams().add(team);
				userRepository.saveAndFlush(userOptional.get());
			}
	    }

	    team = teamRepository.saveAndFlush(team);
	    return teamMapper.entityToDto(team);
	}

	@Override
	public TeamResponseDto deleteTeam(Long teamId) {
		if (teamId == null) {
	        throw new BadRequestException("Team ID must not be null");
	    }

	    Optional<Team> teamToFind = teamRepository.findById(teamId);
	    if (teamToFind.isEmpty()) {
	        throw new NotFoundException("Team not found");
	    }
	    
	    Team team = teamToFind.get();
	    team.setDeleted(true);
	    return teamMapper.entityToDto(teamRepository.saveAndFlush(team));
	    
	}
}
