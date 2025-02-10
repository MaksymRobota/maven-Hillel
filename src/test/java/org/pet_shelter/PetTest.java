package org.pet_shelter;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PetTest {
    @Test
    void testNoArgsConstructor() {
        Pet pet = new Pet();
        assertThat(pet).isNotNull();
    }

    @Test
    void testAllArgsConstructor() {
        Pet pet = new Pet("Buddy", "Golden Retriever", 3);
        assertThat(pet.getName()).isEqualTo("Buddy");
        assertThat(pet.getBreed()).isEqualTo("Golden Retriever");
        assertThat(pet.getAge()).isEqualTo(3);
    }

    @Test
    void testSettersAndGetters() {
        Pet pet = new Pet();
        pet.setName("Luna");
        pet.setBreed("Siberian Husky");
        pet.setAge(2);

        assertThat(pet.getName()).isEqualTo("Luna");
        assertThat(pet.getBreed()).isEqualTo("Siberian Husky");
        assertThat(pet.getAge()).isEqualTo(2);
    }

    @Test
    void testToString() {
        Pet pet = new Pet("Max", "Bulldog", 5);
        String expectedString = "Pet(name=Max, breed=Bulldog, age=5)";
        assertThat(pet.toString()).isEqualTo(expectedString);
    }
}
