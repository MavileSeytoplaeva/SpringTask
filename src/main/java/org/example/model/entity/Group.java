package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "groups")
@JsonIgnoreProperties("id")
public class Group {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String color;
    @Column(name = "ext_id")
    private String extId;

    public Group(@JacksonXmlProperty(localName = "Id") String extId,
                   @JacksonXmlProperty(localName = "Name") String name,
                   @JacksonXmlProperty(localName = "Color") String color
    ) {
        this.extId = extId;
        this.name = name;
        this.color = color;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) && Objects.equals(color, group.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", ext_id='" + extId + '\'' +
                '}';
    }
}
