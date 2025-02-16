package org.pet_shelter.services;

import org.pet_shelter.models.Pet;

import java.util.List;
import java.util.Scanner;

public class AnimalService {
    private final List<Pet> shelter;
    private final String filePath;
    private final Scanner scanner;
    private final PetDataService petDataService;

    public AnimalService(String filePath, Scanner scanner, PetDataService petDataService) {
        this.filePath = filePath;
        this.scanner = scanner;
        this.petDataService = petDataService;

        this.shelter = petDataService.getPets();
    }

    public void addPet() {
        System.out.print("Enter pet name: ");
        String name = scanner.next();


        System.out.print("Enter pet breed: ");
        String breed = scanner.next();

        System.out.print("Enter pet age: ");
        int age = scanner.nextInt();

        shelter.add(new Pet(name, breed, age));
        savePets();

        System.out.println(name + " has been added to the shelter.");
    }

    public void showAllPets() {
        if (shelter.isEmpty()) {
            System.out.println("No pets in the shelter.");
        } else {
            System.out.println("\nList of pets in the shelter:");
            for (int i = 0; i < shelter.size(); i++) {
                System.out.println((i + 1) + ". " + shelter.get(i));
            }
        }
    }

    public void takePet() {
        if (shelter.isEmpty()) {
            System.out.println("No pets available to take.");
            return;
        }

        showAllPets();
        System.out.print("Enter the number of the pet to take: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < shelter.size()) {
            Pet removedPet = shelter.remove(index);
            savePets();

            System.out.println(removedPet.getName() + " has been taken from the shelter.");
        } else {
            System.out.println("Invalid choice. Try again.");
        }
    }

    private void savePets() {
        petDataService.savePets(filePath, shelter);
    }
}
