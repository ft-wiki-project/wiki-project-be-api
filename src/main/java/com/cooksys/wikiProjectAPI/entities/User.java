package com.cooksys.wikiProjectAPI.entities;

import java.util.ArrayList;
import java.util.List;

import com.cooksys.wikiProjectAPI.embeddables.Credentials;
import com.cooksys.wikiProjectAPI.embeddables.Profile;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.JoinColumn;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_table")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private boolean active = false;

  private boolean admin = false;

  private String status = "PENDING";

  @Embedded
  private Profile profile;

  @Embedded
  private Credentials credentials;

  @ManyToMany
  @JoinTable(
    name = "user_team",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "team_id")
  )
  private List<Team> teams = new ArrayList<>();

  @ManyToMany
  @JoinTable(
    name = "user_company",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "company_id")
  )
  private List<Company> companies = new ArrayList<>();
}
