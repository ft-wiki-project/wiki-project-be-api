package com.cooksys.wikiProjectAPI.embeddables;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Profile {
  private String first;
  private String last;

  @Column(nullable = false)
  private String email;
  private String phone;
}
