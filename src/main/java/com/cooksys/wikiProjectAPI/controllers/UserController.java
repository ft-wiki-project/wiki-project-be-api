package com.cooksys.wikiProjectAPI.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.wikiProjectAPI.dtos.CredentialsDto;
import com.cooksys.wikiProjectAPI.dtos.UserRequestDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;
import com.cooksys.wikiProjectAPI.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
  private final UserService userService;

  @PostMapping("/")
  public UserResponseDto createUser(@RequestBody UserRequestDto request) {
	  return userService.createUser(request);

  }

  @PostMapping("/login")
  public UserResponseDto login(@RequestBody CredentialsDto credentialsDto) {
    return userService.login(credentialsDto);
  }
  
  @PatchMapping("/{userId}")
  public UserResponseDto updateUser(@PathVariable Long userId) {
	  return userService.updateUser(userId);
  }
} 
