package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.UserNotFoundException;
import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.example.model.mapper.UserMapper;
import org.example.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userDtoMapper;
    private final PasswordEncoder encoder;


    public UserDto getLoggedInUser(Authentication authentication) {
        return userDtoMapper.mapToUserDto(userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new));
    }


    public UserDto findByEmail(String email) {
        User findedUser = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return userDtoMapper.mapToUserDto(findedUser);
    }

    public User getUser(String userName) {
        return userRepository.findByEmail(userName).orElseThrow(UserNotFoundException::new);
    }

    public boolean userWithEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}

