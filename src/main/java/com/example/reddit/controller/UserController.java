package com.example.reddit.controller;

import com.example.reddit.dto.UserDto;
import com.example.reddit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public UserDto addUser(@RequestBody UserDto user) {
    return userService.addUser(user);
  }

  @GetMapping
  public List<UserDto> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public UserDto getUserById(@PathVariable UUID id) {
    return userService.getUserById(id);
  }

  @PutMapping("/{id}")
  public UserDto updateUser(@RequestBody UserDto user, @PathVariable UUID id) {
    return userService.updateUser(user, id);
  }

  @DeleteMapping("/{id}")
  public UserDto deactivateUser(@PathVariable UUID id) {
    return userService.deactivateUser(id);
  }
}
