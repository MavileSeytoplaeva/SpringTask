package org.example.services;

import org.example.exceptions.NoSuchGroupException;
import org.example.model.dto.Export;
import org.example.model.dto.GroupDto;
import org.example.model.entity.Group;
import org.example.model.mapper.GroupMapper;
import org.example.repositories.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GroupService {
    private final GroupRepository repository;

    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public GroupDto findById(int id) {
        Group group = repository.findById(id).orElseThrow(NoSuchGroupException::new);
        return GroupMapper.mapGroupToGroupDto(group);
    }

    @Transactional
    public void createGroup(GroupDto groupDto) {
        Group group = GroupMapper.mapGroupDtoToGroup(groupDto);
        repository.save(group);
    }

    @Transactional
    public void createGroupsFromXMLFile(Export exportValue) {
        List<Group> groups = exportValue.getGroups().stream()
                .filter(group -> findGroupByExtId(group.getExtId()) == null)
                .toList();
        repository.saveAll(groups);
    }

    @Transactional
    public void editGroup(int id, GroupDto groupDto) {
        Group group = repository.findById(id).orElseThrow(NoSuchGroupException::new);
        group.setName(groupDto.getName());
        group.setColor(groupDto.getColor());
        repository.save(group);
    }

    @Transactional(readOnly = true)
    public List<Group> getAllGroups() {
        return repository.findAll();
    }

    @Transactional
    public void deleteGroup(int id) {
        Group group = repository.findById(id).orElseThrow(NoSuchGroupException::new);
        repository.delete(group);
    }

    @Transactional(readOnly = true)
    public Group findGroupByExtId(String extId) {
        return repository.findByExtId(extId).stream()
                .filter(group -> !group.getName().isEmpty() && !group.getColor().isEmpty())
                .findAny()
                .orElse(null);
    }
}
