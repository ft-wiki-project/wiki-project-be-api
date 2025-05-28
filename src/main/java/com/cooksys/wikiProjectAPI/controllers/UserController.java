package com.cooksys.wikiProjectAPI.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.wikiProjectAPI.dtos.CompanyResponseDto;
import com.cooksys.wikiProjectAPI.dtos.CredentialsDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;
import com.cooksys.wikiProjectAPI.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping("/login")
  public UserResponseDto login(@RequestBody CredentialsDto credentialsDto) {
    return userService.login(credentialsDto);
  }
  
  
  
}
