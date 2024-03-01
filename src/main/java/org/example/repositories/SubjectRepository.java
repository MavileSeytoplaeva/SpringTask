package org.example.repositories;

import org.example.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query(value = "SELECT * FROM subjects WHERE type = ? AND subjects.expiration_date <= ?", nativeQuery = true)
    List<Subject> findAllByTypeAndDateEarlierThanGiven(String type, Date currentDatePlusWarningDays);

}
