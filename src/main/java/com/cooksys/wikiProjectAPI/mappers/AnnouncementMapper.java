package com.cooksys.wikiProjectAPI.mappers;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.wikiProjectAPI.dtos.AnnouncementRequestDto;
import com.cooksys.wikiProjectAPI.dtos.AnnouncementResponseDto;
import com.cooksys.wikiProjectAPI.entities.Announcement;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
  Announcement requestDtoToEntity(AnnouncementRequestDto announcementRequestDto);
  
  @Mapping(target = "author.username", source = "author.credentials.username")
  List<AnnouncementResponseDto> entitiesToDtos(List<Announcement> announcements);
  
  @Mapping(target = "author.username", source = "author.credentials.username")
  AnnouncementResponseDto entityToDto(Announcement announcement);
}
