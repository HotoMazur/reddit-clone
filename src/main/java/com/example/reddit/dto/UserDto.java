package com.example.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id; //not need in request
    private String username; // require
    private String password; //require
    private String email; //require
    private LocalDateTime createAt; //generate automatically
    private boolean enabled; //generate automatically
    private String bio; // optional
    private boolean status; //generate automatically
}
