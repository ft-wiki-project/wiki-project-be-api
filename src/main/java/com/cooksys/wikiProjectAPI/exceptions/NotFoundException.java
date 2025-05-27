package com.cooksys.wikiProjectAPI.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException {
    /**
   * 
   */
  private static final long serialVersionUID = 2431341283765886143L;

  private String message;
}