package com.cooksys.wikiProjectAPI.dtos;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnouncementResponseDto {
  private Long id;
  private String title;
  private String message;
  private Timestamp date;
  private UserResponseDto author;
  private CompanyResponseDto company;
}
