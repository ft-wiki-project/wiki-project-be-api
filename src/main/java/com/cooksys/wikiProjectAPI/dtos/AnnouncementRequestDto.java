package com.cooksys.wikiProjectAPI.dtos;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnouncementRequestDto {
  private String title;
  private String message;
  private CompanyRequestDto company;
  private UserRequestDto author;
}
