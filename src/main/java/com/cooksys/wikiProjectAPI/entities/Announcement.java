package com.cooksys.wikiProjectAPI.entities;

import java.security.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Announcement {
  @Id
  @GeneratedValue
  private Long id;

  @CreationTimestamp
  private Timestamp date;

  private String title;
  
  private String message;
}
