package com.cooksys.wikiProjectAPI.entities;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
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
public class Announcement {
  @Id
  @GeneratedValue
  private Long id;

  @CreationTimestamp
  private Timestamp date;

  private String title;
  
  private String message;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User author;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;
}
