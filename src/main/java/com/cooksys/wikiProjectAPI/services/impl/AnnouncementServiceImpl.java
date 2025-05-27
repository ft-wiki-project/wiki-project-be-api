package com.cooksys.wikiProjectAPI.services.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.wikiProjectAPI.dtos.AnnouncementRequestDto;
import com.cooksys.wikiProjectAPI.dtos.AnnouncementResponseDto;
import com.cooksys.wikiProjectAPI.entities.Announcement;
import com.cooksys.wikiProjectAPI.entities.Company;
import com.cooksys.wikiProjectAPI.entities.User;
import com.cooksys.wikiProjectAPI.exceptions.BadRequestException;
import com.cooksys.wikiProjectAPI.exceptions.NotFoundException;
import com.cooksys.wikiProjectAPI.mappers.AnnouncementMapper;
import com.cooksys.wikiProjectAPI.repositories.AnnouncementRepository;
import com.cooksys.wikiProjectAPI.repositories.CompanyRepository;
import com.cooksys.wikiProjectAPI.repositories.UserRepository;
import com.cooksys.wikiProjectAPI.services.AnnouncementService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
  private final AnnouncementRepository announcementRepository;
  private final AnnouncementMapper announcementMapper;
  private final CompanyRepository companyRepository;
  private final UserRepository userRepository;
	
  @Override
	public List<AnnouncementResponseDto> getAnnouncementsByCompanyId(Long companyId) {
		if (companyId == null) {
      throw new BadRequestException("Company ID cannot be null");
    }

    List<Announcement> announcements = announcementRepository.findByCompanyId(companyId);

    if (announcements.isEmpty()) {
      throw new NotFoundException("No announcements found for company with ID: " + companyId);
    }

    return announcementMapper.entitiesToDtos(announcements);
	}

  @Override
  public AnnouncementResponseDto createAnnouncement(AnnouncementRequestDto announcementRequestDto) {
    if (announcementRequestDto.getCompanyId() == null || announcementRequestDto.getAuthorId() == null) {
      throw new BadRequestException("Company ID and User ID cannot be null");
    }

    Long companyId = announcementRequestDto.getCompanyId();
    Long userId = announcementRequestDto.getAuthorId();

    if (announcementRequestDto == null || 
        announcementRequestDto.getTitle() == null || 
        announcementRequestDto.getMessage() == null) {
      throw new BadRequestException("Invalid announcement request");
    }

    Optional<Company> companyOptional = companyRepository.findById(companyId);
    if (companyOptional.isEmpty()) {
      throw new NotFoundException("Company with ID: " + companyId + " not found");
    }
    Company company = companyOptional.get();

    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isEmpty()) {
      throw new NotFoundException("User with ID: " + userId + " not found");
    }
    User user = userOptional.get();

    Announcement announcement = announcementMapper.requestDtoToEntity(announcementRequestDto);

    announcement.setCompany(company);
    announcement.setAuthor(user);
    return announcementMapper.entityToDto(announcementRepository.saveAndFlush(announcement));
  }
  
}
