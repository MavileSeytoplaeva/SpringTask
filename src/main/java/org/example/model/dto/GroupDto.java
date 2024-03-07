package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    @JsonIgnore
    private int id;
    @JacksonXmlProperty(localName = "Color")
    @NotEmpty(message = "Напишите цвет")
    private String color;
    @JacksonXmlProperty(localName = "Name")
    @NotEmpty(message = "Напишите название")
    private String name;
    @JacksonXmlProperty(localName = "Id")
    private String ext_id;
}
