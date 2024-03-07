package org.example.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.entity.Group;
import org.example.model.entity.Subject;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "Export", namespace = "urn:ABC:ExportData:v1.0:Data")
public class Export {
    @JacksonXmlProperty(localName = "Groups")
    private List<Group> groups = new ArrayList<>();

    @JacksonXmlProperty(localName = "Subjects")
    private List<Subject> subjects = new ArrayList<>();
}
