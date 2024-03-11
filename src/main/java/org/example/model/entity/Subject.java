package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.*;
import org.example.SubjectType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "subjects")
public class Subject {
    @JsonIgnore
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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Group group;
    @Column(name = "ext_id")
    private String extId;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Subject(@JacksonXmlProperty(localName = "Id") String extId,
                   @JacksonXmlProperty(localName = "Name") String name,
                   @JacksonXmlProperty(localName = "Type") SubjectType type,
                   @JacksonXmlProperty(localName = "Date") Date issueDate,
                   @JacksonXmlProperty(localName = "Group") String group,
                   @JacksonXmlProperty(localName = "LifeTypeInDays") int lifeTypeInDays
                   ) {
        this.name = name;
        this.type = type;
        this.issueDate = issueDate;
        LocalDate issueLocalDate = issueDate.toLocalDate();
        this.expirationDate = Date.valueOf(issueLocalDate.plusDays(lifeTypeInDays));
        Group groupObj = new Group();
        groupObj.setExtId(group);
        this.group = groupObj;
        this.extId = extId;
    }

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

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", issueDate=" + issueDate +
                ", expirationDate=" + expirationDate +
                ", group=" + group +
                ", ext_id='" + extId + '\'' +
                '}';
    }
}
