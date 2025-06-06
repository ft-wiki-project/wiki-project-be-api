package com.cooksys.wikiProjectAPI.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {
  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String description;

  @ManyToMany(mappedBy = "companies")
  private List<User> users = new ArrayList<>();

  @OneToMany(mappedBy = "company")
  private List<Team> teams = new ArrayList<>();

  @OneToMany(mappedBy = "company")
  private List<Announcement> announcements = new ArrayList<>();
}
