package app;

import java.util.Scanner;

public class UserInput {

    private Scanner scanner;

    public UserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getValidInput(String prompt, ValidatorFunction validator, String errorMessage) {
        String input = "";
        boolean isValid = false; // Ajout d'un indicateur pour suivre la validité de l'entrée
        while (!isValid) {
            System.out.println(prompt);
            input = scanner.nextLine(); // Utilisation de nextLine() pour lire des lignes complètes
            if (validator.isValid(input)) {
                isValid = true; // Définir l'indicateur sur true si l'entrée est valide
            } else {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    public String getOptionalInput(String prompt, ValidatorFunction validator, String errorMessage) {
        String input;
        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine(); // Utilisation de nextLine() pour lire des lignes complètes
            if (input.isEmpty() || validator.isValid(input)) {
                break;
            } else {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    public int getValidInt(int min, int max) {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Veuillez choisir un nombre valide entre " + min + " et " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Nombre invalide, veuillez réessayer");
            }
        }
        return input;
    }

    @FunctionalInterface
    public interface ValidatorFunction {
        boolean isValid(String input);
    }
}
