package com.cooksys.wikiProjectAPI.dtos;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectResponseDto {
  private Long id;
  private String name;
  private String description;
  private boolean active;
  private TeamResponseDto team;
}
