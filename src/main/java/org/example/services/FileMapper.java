package org.example.services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.dto.Export;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
@Service
public class FileMapper {

    public Export mapXMLFile(MultipartFile file) {
        try {
            System.out.println("Uploaded file: " + file.getOriginalFilename());
            InputStream inputStream = file.getInputStream();
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            return xmlMapper.readValue(inputStream, Export.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
