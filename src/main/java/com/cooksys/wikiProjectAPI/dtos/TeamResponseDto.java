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
  private List<ProjectResponseDto> projects;
  private List<UserResponseDto> users;
  private boolean deleted;
}
