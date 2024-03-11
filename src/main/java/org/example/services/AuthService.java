package org.example.services;

import org.example.config.UserDetailsManagerImpl;
import org.example.model.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserDetailsManagerImpl manager;
    private final PasswordEncoder encoder;

    public AuthService(UserDetailsManagerImpl manager, PasswordEncoder encoder) {
        this.manager = manager;
        this.encoder = encoder;
    }


    public boolean register(UserDto userDto) {
        if (manager.userExists(userDto.getEmail())) {
            return false;
        }
        String pass = encoder.encode(userDto.getPassword());
        manager.createUser(userDto, pass);
        return true;
    }

}

