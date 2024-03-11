package org.example.controllers;

import jakarta.validation.Valid;
import org.example.SubjectType;
import org.example.model.dto.SubjectDto;
import org.example.model.dto.UserDto;
import org.example.model.entity.Group;
import org.example.model.entity.Subject;
import org.example.services.GroupService;
import org.example.services.SubjectService;
import org.example.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;
    private final GroupService groupService;
    private final UserService userService;

    public SubjectController(SubjectService subjectService, GroupService groupService, UserService userService) {
        this.subjectService = subjectService;
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public String showSubjectList(Model model, Authentication authentication) {
        UserDto userDto = userService.getLoggedInUser(authentication);
        List<Subject> subjects = subjectService.getAllSubjectsByUserId(userDto.getId());
        model.addAttribute("subjects", subjects);
        return "subjects/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        SubjectDto subjectDto = new SubjectDto();
        List<Group> groups = groupService.getAllGroups();
        List<SubjectType> typeValues = Arrays.stream(SubjectType.values()).toList();
        model.addAttribute("subjectDto", subjectDto);
        model.addAttribute("typeValues", typeValues);
        model.addAttribute("groups", groups);
        return "subjects/CreateSubject";
    }

    @PostMapping("/create")
    public String createSubject(@Valid @ModelAttribute("subjectDto") SubjectDto subjectDto, BindingResult result, Model model, Authentication authentication) {
        UserDto userDto = userService.getLoggedInUser(authentication);
        List<Group> groups = groupService.getAllGroups();
        List<SubjectType> typeValues = Arrays.stream(SubjectType.values()).toList();
        model.addAttribute("typeValues", typeValues);
        model.addAttribute("groups", groups);
        if (result.hasErrors()) {
            return "subjects/CreateSubject";
        }

        subjectService.createSubject(subjectDto, userDto);
        return "redirect:/subjects";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(Model model, @PathVariable int id) {
        List<Group> groups = groupService.getAllGroups();
        List<SubjectType> typeValues = Arrays.stream(SubjectType.values()).toList();
        SubjectDto subjectDto = subjectService.findById(id);
        model.addAttribute("subjectDto", subjectDto);
        model.addAttribute("typeValues", typeValues);
        model.addAttribute("groups", groups);

        return "subjects/EditSubject";
    }

    @PostMapping("/edit/{id}")
    public String updateSubject(Model model, @PathVariable int id, @Valid @ModelAttribute SubjectDto subjectDto, BindingResult result) {
        List<Group> groups = groupService.getAllGroups();
        List<SubjectType> typeValues = Arrays.stream(SubjectType.values()).toList();
        model.addAttribute("subjectDto", subjectDto);
        model.addAttribute("typeValues", typeValues);
        model.addAttribute("groups", groups);
        if (result.hasErrors()) {
            return "subjects/EditSubject";
        }
        subjectService.editSubject(id, subjectDto);
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable int id) {
        subjectService.deleteById(id);
        return "redirect:/subjects";
    }
    @GetMapping("/generateReport")
    public String showGenerateReportPage(Model model) {
        SubjectDto subjectDto = new SubjectDto();
        List<SubjectType> typeValues = Arrays.stream(SubjectType.values()).toList();

        model.addAttribute("subjectDto", subjectDto);
        model.addAttribute("typeValues", typeValues);

        return "subjects/GenerateReport";
    }

    @PostMapping("/generateReport")
    public String generateReport(@ModelAttribute("subjectDto") SubjectDto subjectDto, Model model, Authentication authentication) {
        UserDto userDto = userService.getLoggedInUser(authentication);
        List<Subject> subjectsWithExpirationDate = subjectService.getSubjectsWithNearExpirationDateByTypeAndUserId(subjectDto.getType(), userDto);
        model.addAttribute("subjects", subjectsWithExpirationDate);

        return "subjects/Report";
    }
}
