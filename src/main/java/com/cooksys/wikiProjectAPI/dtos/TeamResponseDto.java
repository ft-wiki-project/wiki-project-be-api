package com.cooksys.wikiProjectAPI.dtos;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamResponseDto {
  private Long id;
  private String name;
  private String description;
  private CompanyResponseDto company;
  private List<UserResponseDto> users;
  private List<ProjectResponseDto> projects;
}
