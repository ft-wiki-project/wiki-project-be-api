package com.cooksys.wikiProjectAPI.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.wikiProjectAPI.dtos.AnnouncementRequestDto;
import com.cooksys.wikiProjectAPI.dtos.AnnouncementResponseDto;
import com.cooksys.wikiProjectAPI.services.AnnouncementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
public class AnnouncementController {
  private final AnnouncementService announcementService;

  @GetMapping("/{companyId}")
  public List<AnnouncementResponseDto> getAnnouncementsByCompanyId(@PathVariable Long companyId) {
    return announcementService.getAnnouncementsByCompanyId(companyId);
  }

  @PostMapping()
  public AnnouncementResponseDto createAnnouncement(@RequestBody AnnouncementRequestDto announcementRequestDto) {
    return announcementService.createAnnouncement(announcementRequestDto);
  }
}
