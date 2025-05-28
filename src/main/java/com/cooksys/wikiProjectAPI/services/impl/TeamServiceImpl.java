package com.cooksys.wikiProjectAPI.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.wikiProjectAPI.dtos.TeamRequestDto;
import com.cooksys.wikiProjectAPI.dtos.TeamResponseDto;
import com.cooksys.wikiProjectAPI.entities.Company;
import com.cooksys.wikiProjectAPI.entities.Team;
import com.cooksys.wikiProjectAPI.entities.User;
import com.cooksys.wikiProjectAPI.exceptions.BadRequestException;
import com.cooksys.wikiProjectAPI.mappers.TeamMapper;
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
			throw new BadRequestException("Company does not exsist");
		}
		
		Optional<Company> companyToFind = companyRepository.findById(companyId);
		
		List<Team> teamsInCompany = companyToFind.get().getTeams();
		
		return teamMapper.entitiesToDtos(teamsInCompany);
		
	}
}
