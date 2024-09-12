package com.example.reddit.service;

import com.example.reddit.dto.UserDto;
import com.example.reddit.entity.UserEntity;
import com.example.reddit.exception.NotFoundException;
import com.example.reddit.mapper.UserMapper;
import com.example.reddit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDto addUser(UserDto userDto) {
    UserEntity user = UserMapper.INSTANCE.dtoToEntity(userDto);
    System.out.println(user.getCreateAt());
    if (user.getCreateAt() == null){
      user.setCreateAt(LocalDateTime.now());
    }
    if (!user.isStatus()){
      user.setStatus(true);
    }
    if (!user.isEnabled()){
      user.setEnabled(false);
    }
    UserEntity createUser = userRepository.save(user);

    return UserMapper.INSTANCE.entityToDTO(createUser);
  }

  public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream()
        .map(UserMapper.INSTANCE::entityToDTO)
        .collect(Collectors.toList());
  }

  public UserDto getUserById(UUID id) {
    UserEntity user =
        userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));
    return UserMapper.INSTANCE.entityToDTO(user);
  }

  public UserDto updateUser(UserDto updatedUser, UUID id) {
    UserEntity user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User with ID " + id + " was not found"));

    UserMapper.INSTANCE.updateFromDTO(updatedUser, user);
    user = userRepository.save(user);
    return UserMapper.INSTANCE.entityToDTO(user);
  }

  public UserDto deactivateUser(UUID id) {
    return userRepository.findById(id)
            .map(user -> {
              user.setStatus(false);
              user = userRepository.save(user);
              return UserMapper.INSTANCE.entityToDTO(user);
            })
            .orElseThrow(() -> new NotFoundException("User with ID " + id + " was not found"));
  }
}
