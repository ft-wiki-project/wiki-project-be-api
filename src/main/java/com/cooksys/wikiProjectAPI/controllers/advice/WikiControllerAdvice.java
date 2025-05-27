package com.cooksys.wikiProjectAPI.controllers.advice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.cooksys.wikiProjectAPI.dtos.ErrorDto;
import com.cooksys.wikiProjectAPI.exceptions.BadRequestException;
import com.cooksys.wikiProjectAPI.exceptions.NotAuthorizedException;
import com.cooksys.wikiProjectAPI.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = { "com.cooksys.wikiProjectAPI.controllers" })
@ResponseBody
public class WikiControllerAdvice {
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException e) {
    return new ErrorDto(e.getMessage());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ErrorDto handleNotFoundException(HttpServletRequest request, NotFoundException e) {
    return new ErrorDto(e.getMessage());
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(NotAuthorizedException.class)
  public ErrorDto handleUnauthorizedException(HttpServletRequest request, NotAuthorizedException e) {
    return new ErrorDto(e.getMessage());
  }
}
