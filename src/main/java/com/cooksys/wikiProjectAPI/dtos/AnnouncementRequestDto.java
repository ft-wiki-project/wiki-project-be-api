package com.cooksys.wikiProjectAPI.dtos;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnouncementRequestDto {
  private String title;
  private String message;
  private Timestamp date;
  private Long companyId;
  private Long authorId;
}
