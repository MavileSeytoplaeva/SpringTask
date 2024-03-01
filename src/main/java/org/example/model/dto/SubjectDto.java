package org.example.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.SubjectType;
import org.example.model.entity.Group;

import java.sql.Date;

@Data
public class SubjectDto {
    private int id;
    @NotEmpty(message = "Напишите имя")
    private String name;
    @NotNull(message = "Напишите тип")
    private SubjectType type;
    @NotNull(message = "Напишите дату выдачи")
    private Date issueDate;
    @NotNull(message = "Напишите дату окончания срока действия")
    private Date expirationDate;
    @NotNull(message = "Укажите группу")
    private Group group;
}
