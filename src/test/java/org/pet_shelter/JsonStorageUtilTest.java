package org.pet_shelter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class JsonStorageUtilTest {
    private final ObjectMapper objectMapper = mock(ObjectMapper.class);
    private final JsonStorageUtil jsonStorageUtil = new JsonStorageUtil();

    @Test
    void testSavePets() throws IOException {
        String filePath = "test/pets.json";
        List<Pet> expectedPets = List.of(new Pet("Buddy", "Dog", 1), new Pet("Mittens", "Cat", 2));

        when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(expectedPets);

        jsonStorageUtil.savePets(filePath, expectedPets);
        List<Pet> actualPets = jsonStorageUtil.loadPets(filePath);

        assertThat(actualPets)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedPets);
    }

    @Test
    void testLoadPets() throws IOException {
        String filePath = "test/pets.json";
        List<Pet> expectedPets = List.of(new Pet("Buddy", "Dog", 1), new Pet("Mittens", "Cat", 2));

        when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(expectedPets);

        List<Pet> actualPets = jsonStorageUtil.loadPets(filePath);

        assertThat(actualPets)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedPets);
    }

    @Test
    void testLoadPets_validFile(@TempDir Path tempDir) throws Exception {
        String filePath = "test/pets.json";
        List<Pet> expectedPets = List.of(new Pet("Buddy", "Dog", 1), new Pet("Mittens", "Cat", 2));

        when(objectMapper.readValue(eq(filePath), any(TypeReference.class)))
                .thenReturn(expectedPets);

        jsonStorageUtil.savePets(filePath, expectedPets);
        List<Pet> actualPets = JsonStorageUtil.loadPets(filePath);

        assertThat(actualPets)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedPets);
    }

    @Test
    void testLoadPets_fileDoesNotExist() {
        List<Pet> actualPets = JsonStorageUtil.loadPets("non_existent_file.json");

        assertNotNull(actualPets);
        assertTrue(actualPets.isEmpty());
    }
}
