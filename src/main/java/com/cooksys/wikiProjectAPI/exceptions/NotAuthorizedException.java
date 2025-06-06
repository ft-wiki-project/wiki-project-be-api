package com.cooksys.wikiProjectAPI.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotAuthorizedException extends RuntimeException {
    /**
   * 
   */
  private static final long serialVersionUID = 8919760638344726468L;

  private String message;
}
