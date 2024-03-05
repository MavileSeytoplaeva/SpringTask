package org.example.model.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class GroupDto {
    private int id;
    @NotEmpty(message = "Напишите название")
    private String name;
    @NotEmpty(message = "Напишите цвет")
    private String color;
}
