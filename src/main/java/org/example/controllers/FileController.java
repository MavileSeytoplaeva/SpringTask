package org.example.controllers;

import org.example.model.dto.*;
import org.example.services.FileMapper;
import org.example.services.GroupService;
import org.example.services.SubjectService;
import org.example.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/file")
public class FileController {

    private final SubjectService subjectService;
    private final GroupService groupService;
    private final FileMapper fileMapper;
    private final UserService userService;

    public FileController(SubjectService subjectService, GroupService groupService, FileMapper fileMapper, UserService userService) {
        this.subjectService = subjectService;
        this.groupService = groupService;
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    @GetMapping
    public String showSetFilePage() {
        return "files/index";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public String handleUpload(@RequestParam("file") MultipartFile file, Authentication authentication) {
        UserDto userDto = userService.getLoggedInUser(authentication);
        Export exportValue = fileMapper.mapXMLFile(file);
        groupService.createGroupsFromXMLFile(exportValue);
        subjectService.createSubjectsFromXMLFile(exportValue, userDto);

        return "redirect:/subjects";
    }
}
