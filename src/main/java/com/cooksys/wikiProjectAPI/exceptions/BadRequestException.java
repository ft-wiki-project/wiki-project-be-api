package com.cooksys.wikiProjectAPI.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException {
    /**
   * 
   */
  private static final long serialVersionUID = 4987251530297546468L;

  private String message;
}
