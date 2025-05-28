package com.cooksys.wikiProjectAPI.controllers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.wikiProjectAPI.dtos.CredentialsDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;
import com.cooksys.wikiProjectAPI.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/login")
  public UserResponseDto login(@RequestBody CredentialsDto credentialsDto) {
    return userService.login(credentialsDto);
  }
}
