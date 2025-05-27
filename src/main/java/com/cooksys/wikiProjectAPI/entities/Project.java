package com.cooksys.wikiProjectAPI.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String description;
  private boolean active = true;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;
}
