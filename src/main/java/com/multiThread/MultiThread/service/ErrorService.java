package com.multiThread.MultiThread.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.multiThread.MultiThread.entity.Error;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ErrorService {

    @Value("${errors.file.json.fileName}")
    private String filePath;

    public void writeErrors(List<Error> errorList) {
        ObjectMapper objectMapper = new ObjectMapper();
        // pretty print
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File file = new File(filePath);
        List<Error> existingErrors = new ArrayList<>();

        try {
            // read existing errors from the json file if it exists and is not empty
            if (file.exists() && file.length() != 0) {
                existingErrors = objectMapper.readValue(file, new TypeReference<List<Error>>() {});
            }
            // add new errors to the existing list
            existingErrors.addAll(errorList);

            // write the updated error list to the file
            objectMapper.writeValue(file, existingErrors);
            System.out.println("Errors have been updated in the json file.");
        } catch (JsonParseException | JsonMappingException e) {
            System.err.println("Error parsing the JSON file: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
