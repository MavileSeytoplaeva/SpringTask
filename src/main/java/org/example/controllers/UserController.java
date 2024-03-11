package org.example.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.SubjectType;
import org.example.model.dto.SubjectDto;
import org.example.model.dto.UserDto;
import org.example.model.entity.Group;
import org.example.services.AuthService;
import org.example.services.UserService;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
//@Slf4j
public class UserController {
    private final UserService userService;

    private final AuthService authService;
    private Logger log;

    @GetMapping("/login")
    public String showLoginPage() {
        return "users/login";
    }

//    @PostMapping("/login")
//    public String login(@) {
//        log.warn("login post");
//
//        if (!authService.login(userDto)) {
//            System.out.println("error3");
//
//            return "/static/index";
//        } else {
//            System.out.println("error4");
//
//            return "subjects/index";
//        }
//    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "users/registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/registration";
        }
        if (userService.userWithEmailExists(userDto.getEmail())) {
            model.addAttribute("errorMessage", "Пользователь с электронным адресом " + userDto.getEmail() + " уже зарегистрирован");
            return "users/registration";
        }
        authService.register(userDto);
        return "redirect:/home";
    }

}

