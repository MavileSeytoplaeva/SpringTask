package org.example.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.SubjectType;

import java.sql.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private SubjectType type;
    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date")
    private Date issueDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date")
    private Date expirationDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name) && type == subject.type && Objects.equals(issueDate, subject.issueDate) && Objects.equals(expirationDate, subject.expirationDate) && Objects.equals(group, subject.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, issueDate, expirationDate, group);
    }
}
