package com.cooksys.wikiProjectAPI.services;

import java.util.List;

import com.cooksys.wikiProjectAPI.dtos.AnnouncementRequestDto;
import com.cooksys.wikiProjectAPI.dtos.AnnouncementResponseDto;

public interface AnnouncementService {

  List<AnnouncementResponseDto> getAnnouncementsByCompanyId(Long companyId);

  AnnouncementResponseDto createAnnouncement(AnnouncementRequestDto announcementRequestDto);

}
