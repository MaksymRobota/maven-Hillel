package org.pet_shelter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonStorageUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void savePets(String filePath, List<Pet> shelter) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            objectMapper.writeValue(new File(filePath), shelter);
        } catch (IOException e) {
            System.out.println("Error saving pets: " + e.getMessage());
        }
    }

    public static List<Pet> loadPets(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, new TypeReference<List<Pet>>() {
                });
            } catch (IOException e) {
                System.out.println("Error loading pets: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }
}
