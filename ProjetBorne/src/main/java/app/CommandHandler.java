package app;

import java.util.Scanner;

import model.Client;
import service.ClientService;

public class CommandHandler {
    private ClientService clientService;
    private UserInput userInput;
    private Scanner scanner;
    private Application app;

    public CommandHandler(Application app) {
        try {
            this.clientService = new ClientService();
            this.scanner = new Scanner(System.in);
            this.userInput = new UserInput(this.scanner);
            this.app = app;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCommand(int command, String menu) {
        switch (menu) {
            case "MAIN":
                handleMainCommand(command);
                break;
            case "ACTION":
                handleActionCommand(command);
                break;
            default:
                System.out.println("Menu inconnu");
        }
    }

    private void handleActionCommand(int command) {
        
    }

    private void handleMainCommand(int command) {
        switch(command) {
            case 0:
                break;
            case 1:
                inscription();
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    public void inscription() {
        System.out.println("Vous avez choisi l'inscription\n");

        String nom = userInput.getValidInput("Veuillez saisir votre nom", Validator::isValidName, "Nom invalide. Veuillez réessayer.");
        String prenom = userInput.getValidInput("Veuillez saisir votre prénom", Validator::isValidName, "Prénom invalide. Veuillez réessayer.");
        String adresse = userInput.getValidInput("Veuillez saisir votre adresse", Validator::isValidAddress, "Adresse invalide. Veuillez réessayer.");
        String mail = userInput.getValidInput("Veuillez saisir votre adresse mail", Validator::isValidEmail, "Adresse mail invalide. Veuillez réessayer.");
        String carte = userInput.getValidInput("Veuillez saisir votre numéro de carte de crédit", Validator::isValidCreditCard, "Numéro de carte de crédit invalide. Veuillez réessayer.");
        String numeroMobile = userInput.getValidInput("Veuillez saisir votre numéro mobile", Validator::isValidMobileNumber, "Numéro mobile invalide. Veuillez réessayer.");
        String plaque = userInput.getOptionalInput("Veuillez saisir votre plaque d'immatriculation (optionnel, à laisser vide si voulu)", Validator::isValidLicensePlate, "Plaque d'immatriculation invalide. Veuillez réessayer.");

        // Création d'un objet Client avec les données saisies
        Client client = new Client(nom, prenom, adresse, numeroMobile, mail, carte, plaque);

        // Insertion du client dans la base de données
        try {
            clientService.addClient(client);
            System.out.println("Inscription réussie !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
