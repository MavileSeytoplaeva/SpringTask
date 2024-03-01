package org.example.model.mapper;

import org.example.model.dto.SubjectDto;
import org.example.model.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {
    public static Subject mapSubjectDtoToSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setName(subjectDto.getName());
        subject.setType(subjectDto.getType());
        subject.setIssueDate(subjectDto.getIssueDate());
        subject.setExpirationDate(subjectDto.getExpirationDate());
        subject.setGroup(subjectDto.getGroup());
        return subject;
    }

    public static SubjectDto mapSubjectToSubjectDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setType(subject.getType());
        subjectDto.setIssueDate(subject.getIssueDate());
        subjectDto.setExpirationDate(subject.getExpirationDate());
        subjectDto.setGroup(subject.getGroup());
        return subjectDto;
    }
}


