package org.example.model.mapper;

import org.example.model.dto.GroupDto;
import org.example.model.entity.Group;

public class GroupMapper {
    public static Group mapGroupDtoToGroup(GroupDto groupDto) {
        Group group = new Group();
        group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        group.setColor(groupDto.getColor());
        return group;
    }

    public static GroupDto mapGroupToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        groupDto.setColor(group.getColor());
        return groupDto;
    }
}
