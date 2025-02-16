package org.pet_shelter.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.Getter;
import org.pet_shelter.models.Pet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.pet_shelter.models.exception.ErrorCode.ERROR_001;

public class PetDataService {
    private ObjectMapper mapper;
    @Getter
    private List<Pet> pets;

    public PetDataService(String path) {
        this.mapper = new JsonMapper();

        CollectionType pets = mapper.getTypeFactory()
                .constructCollectionType(List.class, Pet.class);
        File file = new File(path);
        try {
            if (!file.exists() || file.length() == 0) {
                mapper.writeValue(file, new ArrayList<Pet>());
            }
            this.pets = mapper.readValue(file, pets);
        } catch (IOException e) {
            System.err.println(ERROR_001.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void savePets(String filePath, List<Pet> shelter) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            mapper.writeValue(new File(filePath), shelter);
        } catch (IOException e) {
            System.out.println("Error saving pets: " + e.getMessage());
        }
    }
}
