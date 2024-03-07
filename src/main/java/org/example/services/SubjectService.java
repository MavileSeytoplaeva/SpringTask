package org.example.services;

import org.example.SubjectType;
import org.example.exceptions.NoSuchSubjectException;
import org.example.model.dto.Export;
import org.example.model.dto.SubjectDto;
import org.example.model.entity.Group;
import org.example.model.entity.Subject;
import org.example.model.mapper.SubjectMapper;
import org.example.repositories.SubjectRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class SubjectService {
    private final SubjectRepository repository;
    private final GroupService groupService;

    public SubjectService(SubjectRepository repository, GroupService groupService) {
        this.repository = repository;
        this.groupService = groupService;
    }

    @Transactional(readOnly = true)
    public SubjectDto findById(int id) {
        Subject subject = repository.findById(id).orElseThrow(NoSuchSubjectException::new);
        return SubjectMapper.mapSubjectToSubjectDto(subject);
    }
    @Transactional
    public void createSubject(SubjectDto subjectDto) {
        Subject subject = SubjectMapper.mapSubjectDtoToSubject(subjectDto);
        repository.save(subject);
    }

    @Transactional
    public void createSubjectsFromXMLFile(Export exportValue) {
        List<Subject> subjects = exportValue.getSubjects().stream()
                .peek(subject -> {
                    Group group = groupService.findGroupByExtId(subject.getGroup().getExtId());
                    subject.setGroup(group);
                })
                .filter(subject -> findByNameAndType(subject.getName(), subject.getType()).isEmpty())
                .toList();
        repository.saveAll(subjects);
    }

    @Transactional(readOnly = true)
    public List<Subject> findByNameAndType(String name, SubjectType type) {
        return repository.findAlLByNameAndType(name, type);
    }
    @Transactional
    public void editSubject(int id, SubjectDto subjectDto) {
        Subject subject = repository.findById(id).orElseThrow(NoSuchSubjectException::new);
        subject.setName(subjectDto.getName());
        subject.setType(subjectDto.getType());
        subject.setIssueDate(subjectDto.getIssueDate());
        subject.setExpirationDate(subjectDto.getExpirationDate());
        subject.setGroup(subjectDto.getGroup());
        repository.save(subject);
    }
    @Transactional(readOnly = true)
    public List<Subject> getAllSubjects() {
        return repository.findAll();
    }
    @Transactional
    public void deleteSubject(int id) {
        Subject subject = repository.findById(id).orElseThrow(NoSuchSubjectException::new);
        repository.delete(subject);
    }
    @Transactional(readOnly = true)
    public List<Subject> getSubjectsWithNearExpirationDateByType(SubjectType type) {
        Date currentDatePlusWarningDate = type.getExpirationDate();
        List<Subject> subjectsByTypeAndDateEarlierThanGiven = repository.findAll(where(expirationDateBeforeCurrentDatePlusWarningDate(currentDatePlusWarningDate)).and(typeContains(type)));
        System.out.println("subjectsByTypeAndDateEarlierThanGiven = " + subjectsByTypeAndDateEarlierThanGiven);
        System.out.println("currentDatePlusWarningDate = " + currentDatePlusWarningDate);
        return subjectsByTypeAndDateEarlierThanGiven;
    }

    private Specification<Subject> expirationDateBeforeCurrentDatePlusWarningDate(Date currentDatePlusWarningDate){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("expirationDate"), currentDatePlusWarningDate);
    }
    private Specification<Subject> typeContains (SubjectType type){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("type"), type);
    }

}