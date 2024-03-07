package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.SubjectType;
import org.example.model.entity.Group;

import java.sql.Date;

@Data
public class SubjectDto {
    @JsonIgnore
    private int id;
    @JacksonXmlProperty(localName = "Name")
    @NotEmpty(message = "Напишите имя")
    private String name;
    @JacksonXmlProperty(localName = "Type")
    @NotNull(message = "Напишите тип")
    private SubjectType type;
    @JacksonXmlProperty(localName = "Date")
    @NotNull(message = "Напишите дату выдачи")
    private Date issueDate;
    @NotNull(message = "Напишите дату окончания срока действия")
    private Date expirationDate;
    @JacksonXmlProperty(localName = "Group")
    @NotNull(message = "Укажите группу")
    private Group group;
    @JacksonXmlProperty(localName = "LifeTypeInDays")
    private int lifeTypeInDays;
    @JacksonXmlProperty(localName = "Id")
    private String ext_id;
}
