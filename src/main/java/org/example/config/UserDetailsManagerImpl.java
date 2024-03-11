package org.example.config;


import lombok.RequiredArgsConstructor;
import org.example.exceptions.UserNotFoundException;
import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.example.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsManagerImpl implements UserDetailsManager {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(UserDetailsImpl::new)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void createUser(UserDetails userDetails) {
    }

    public void createUser(UserDto userDto, String pass) {
        User user = new User();
        user.setPassword(pass);
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        userRepository.save(user);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }
}

