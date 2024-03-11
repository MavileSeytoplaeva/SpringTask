package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.UserNotFoundException;
import org.example.model.dto.UserDto;
import org.example.model.mapper.UserMapper;
import org.example.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getLoggedInUser(Authentication authentication) {
        return UserMapper.mapToUserDto(userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new));
    }

    public boolean userWithEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}

