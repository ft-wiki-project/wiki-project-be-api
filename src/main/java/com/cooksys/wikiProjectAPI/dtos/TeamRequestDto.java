package com.cooksys.wikiProjectAPI.dtos;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamRequestDto {
  private String name;
  private String description;
  private List<UserRequestDto> users;
  private CompanyRequestDto company;
}
