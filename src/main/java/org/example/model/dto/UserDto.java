package org.example.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty(message = "Напишите электронный адрес")
    @Email(message = "Неправильный формат электронного адреса")
    private String email;
    @NotEmpty(message = "Напишите имя")
    private String name;
    @NotEmpty(message = "Напишите пароль")
    @Size(min = 8, message = "Пароль должен содержать не менее 8 символов")
    private String password;

}
