package org.pet_shelter;

import org.pet_shelter.models.MenuOptions;
import org.pet_shelter.services.AnimalService;
import org.pet_shelter.services.PetDataService;

import java.util.Scanner;

import static org.pet_shelter.util.AppConstants.ANIMAL_PATH;

public class Main {
    private final Scanner scanner;
    private final AnimalService animalService;
    private final PetDataService petDataService;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.petDataService = new PetDataService(ANIMAL_PATH);
        this.animalService = new AnimalService(ANIMAL_PATH, scanner, petDataService);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        boolean isRunning = true;

        do {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.next();

            MenuOptions selectedOptions = MenuOptions.fromKey(choice);
            if (selectedOptions == null) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            switch (selectedOptions) {
                case ADD_PET -> animalService.addPet();
                case SHOW_ALL -> animalService.showAllPets();
                case TAKE_PET -> animalService.takePet();
                case EXIT -> {
                    System.out.println("Exiting... Goodbye!");
                    isRunning = false;
                }
            }
        } while (isRunning);

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\nUser Menu:");
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(option.getKey() + ". " + option.getDescription());
        }
    }
}
