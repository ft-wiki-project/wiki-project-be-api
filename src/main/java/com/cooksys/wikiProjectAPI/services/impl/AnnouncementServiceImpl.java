package com.cooksys.wikiProjectAPI.services.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.wikiProjectAPI.dtos.AnnouncementResponseDto;
import com.cooksys.wikiProjectAPI.entities.Announcement;
import com.cooksys.wikiProjectAPI.exceptions.BadRequestException;
import com.cooksys.wikiProjectAPI.exceptions.NotFoundException;
import com.cooksys.wikiProjectAPI.mappers.AnnouncementMapper;
import com.cooksys.wikiProjectAPI.repositories.AnnouncementRepository;
import com.cooksys.wikiProjectAPI.services.AnnouncementService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
  private final AnnouncementRepository announcementRepository;
  private final AnnouncementMapper announcementMapper;
	
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
  
 

}
