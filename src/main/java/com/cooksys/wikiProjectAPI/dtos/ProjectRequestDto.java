package com.cooksys.wikiProjectAPI.dtos;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectRequestDto {
  private String name;
  private String description;
  private boolean active;
  private Long teamId;
}
