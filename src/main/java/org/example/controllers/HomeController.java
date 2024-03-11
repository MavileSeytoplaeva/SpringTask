package org.example.controllers;

import org.example.model.dto.UserDto;
import org.example.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping()
public class HomeController {
    private final UserService userService;

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
