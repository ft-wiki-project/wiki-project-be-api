package com.cooksys.wikiProjectAPI.entities;

import com.cooksys.wikiProjectAPI.embeddables.Credentials;
import com.cooksys.wikiProjectAPI.embeddables.Profile;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
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
}
