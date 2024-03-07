package org.example.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.Subject;

import java.util.List;
@Getter
@Setter
public class Subjects {
    @JacksonXmlProperty(localName = "Subject")
    private List<Subject> subjectList;


}
