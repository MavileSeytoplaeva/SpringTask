package org.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.SubjectType;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

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
}
