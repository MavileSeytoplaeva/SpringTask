package org.example.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.Group;

import java.util.List;

@Getter
@Setter
public class Groups {
    @JacksonXmlProperty(localName = "Group")
    private List<Group> groupList;

}

