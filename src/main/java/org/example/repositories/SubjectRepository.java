package org.example.repositories;

import org.example.SubjectType;
import org.example.model.entity.Subject;
import org.example.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>, JpaSpecificationExecutor<Subject> {
        List<Subject> findAlLByNameAndTypeAndUser(String name, SubjectType type, User user);

        List<Subject> findAlLByUserId(int userId);

}
