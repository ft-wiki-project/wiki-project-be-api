package com.cooksys.wikiProjectAPI.dtos;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
  private String username;
  private Long id;
  private ProfileDto profile;
  private String status;
  private String admin;
  private String active;
  private List<ProjectResponseDto> projects;
  private List<CompanyResponseDto> companies;
}
