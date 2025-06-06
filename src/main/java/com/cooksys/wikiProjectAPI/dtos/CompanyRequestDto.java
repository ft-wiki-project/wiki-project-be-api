package com.cooksys.wikiProjectAPI.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyRequestDto {
  private String name;
  private String description;
}
