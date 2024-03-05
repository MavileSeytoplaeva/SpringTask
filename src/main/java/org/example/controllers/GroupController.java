package org.example.controllers;

import jakarta.validation.Valid;
import org.example.model.dto.GroupDto;
import org.example.model.entity.Group;
import org.example.services.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping({"", "/"})
    public String showGroupsList(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "groups/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        GroupDto groupDto = new GroupDto();
        model.addAttribute("groupDto", groupDto);
        return "groups/CreateGroup";
    }

    @PostMapping("/create")
    public String createGroup(@Valid @ModelAttribute("groupDto") GroupDto groupDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "groups/CreateGroup";
        }

        groupService.createGroup(groupDto);
        return "redirect:/groups";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(Model model, @PathVariable int id) {
        GroupDto groupDto = groupService.findById(id);
        model.addAttribute("groupDto", groupDto);

        return "groups/EditGroup";
    }

    @PostMapping("/edit/{id}")
    public String updateGroup(Model model, @PathVariable int id, @Valid @ModelAttribute GroupDto groupDto, BindingResult result) {
        model.addAttribute("groupDto", groupDto);
        if (result.hasErrors()) {
            return "groups/EditGroup";
        }
        groupService.editGroup(id, groupDto);
        return "redirect:/groups";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }
}
