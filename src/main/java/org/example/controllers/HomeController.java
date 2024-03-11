package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.UserDto;
import org.example.model.entity.Subject;
import org.example.model.entity.User;
import org.example.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public String homePage(Model model, Authentication authentication) {
        if (authentication != null) {
            UserDto userDto = userService.getLoggedInUser(authentication);
            model.addAttribute("userDto", userDto);
            return "homePage/index";
        }

        return "homePage/index";
    }
}
