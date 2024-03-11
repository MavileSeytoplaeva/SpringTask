package org.example.model.mapper;

import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User mapToUser(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}

