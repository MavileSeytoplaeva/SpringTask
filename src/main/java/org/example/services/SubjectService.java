package org.example.services;

import org.example.SubjectType;
import org.example.exceptions.NoSuchSubjectException;
import org.example.model.dto.SubjectDto;
import org.example.model.entity.Group;
import org.example.model.entity.Subject;
import org.example.model.mapper.SubjectMapper;
import org.example.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@Service
public class SubjectService {
    private SubjectRepository repository;

    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public SubjectDto findById(int id) {
        Subject subject = repository.findById(id).orElseThrow(NoSuchSubjectException::new);
        return SubjectMapper.mapSubjectToSubjectDto(subject);
    }

    public void createSubject(SubjectDto subjectDto) {
        Subject subject = SubjectMapper.mapSubjectDtoToSubject(subjectDto);
        repository.save(subject);
    }

    public void editSubject(SubjectDto subjectDto) {
        Subject subject = SubjectMapper.mapSubjectDtoToSubject(subjectDto);
        repository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return repository.findAll();
    }

    public void deleteSubject(int id) {
        Subject subject = repository.findById(id).orElseThrow(NoSuchSubjectException::new);
        repository.delete(subject);
    }
    public List<Subject>  getSubjectsWithNearExpirationDateByType(SubjectType type) {
        Date currentDatePlusWarningDate = type.getExpirationDate();
        List<Subject> subjectsByTypeAndDateEarlierThanGiven = repository.findAllByTypeAndDateEarlierThanGiven(type.name(), currentDatePlusWarningDate);
        return subjectsByTypeAndDateEarlierThanGiven;
    }
}
