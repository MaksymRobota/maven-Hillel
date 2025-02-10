package org.pet_shelter;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_PATH = "src/main/resources/store/shelter_data.json";
    private static final List<Pet> shelter = JsonStorageUtil.loadPets(FILE_PATH);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Add pet");
            System.out.println("2. Show all");
            System.out.println("3. Take pet from shelter");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addPet();
                    break;
                case "2":
                    showAllPets();
                    break;
                case "3":
                    takePet();
                    break;
                case "4":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addPet() {
        String name = InputValidator.getValidString("Enter pet name: ");
        String breed = InputValidator.getValidString("Enter pet breed: ");
        int age = InputValidator.getValidAge("Enter pet age: ");

        shelter.add(new Pet(name, breed, age));
        JsonStorageUtil.savePets(FILE_PATH, shelter);

        System.out.println(name + " has been added to the shelter.");
    }

    private static void showAllPets() {
        if (shelter.isEmpty()) {
            System.out.println("No pets in the shelter.");
        } else {
            System.out.println("\nList of pets in the shelter:");
            for (int i = 0; i < shelter.size(); i++) {
                System.out.println((i + 1) + ". " + shelter.get(i));
            }
        }
    }

    private static void takePet() {
        if (shelter.isEmpty()) {
            System.out.println("No pets available to take.");
            return;
        }

        showAllPets();
        System.out.print("Enter the number of the pet to take: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < shelter.size()) {
            Pet removedPet = shelter.remove(index);
            JsonStorageUtil.savePets(FILE_PATH, shelter);

            System.out.println(removedPet.getName() + " has been taken from the shelter.");
        } else {
            System.out.println("Invalid choice. Try again.");
        }
    }
}
