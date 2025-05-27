package com.cooksys.wikiProjectAPI.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {
  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String description;

  @ManyToMany(mappedBy = "teams")
  private List<User> users = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

  @OneToMany(mappedBy = "team")
  private List<Project> projects = new ArrayList<>();
}
