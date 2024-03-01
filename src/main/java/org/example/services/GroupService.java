package org.example.services;

import org.example.exceptions.NoSuchGroupException;
import org.example.exceptions.NoSuchSubjectException;
import org.example.model.dto.GroupDto;
import org.example.model.entity.Group;
import org.example.model.mapper.GroupMapper;
import org.example.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository repository;

    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

    public GroupDto findById(int id) {
        Group group = repository.findById(id).orElseThrow(NoSuchGroupException::new);
        return GroupMapper.mapGroupToGroupDto(group);
    }

    public void createGroup(GroupDto groupDto) {
        Group group = GroupMapper.mapGroupDtoToGroup(groupDto);
        repository.save(group);
    }

    public void editGroup(GroupDto groupDto) {
        Group group = GroupMapper.mapGroupDtoToGroup(groupDto);
        repository.save(group);
    }

    public List<Group> getAllGroups() {
        return repository.findAll();
    }

    public void deleteGroup(int id) {
        Group group = repository.findById(id).orElseThrow(NoSuchGroupException::new);
        repository.delete(group);
    }
}
