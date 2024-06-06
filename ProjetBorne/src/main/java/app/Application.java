package app;

import java.util.Scanner;

import data.DatabaseManager;

public class Application {
    private DatabaseManager databaseManager;
    private CommandHandler commandHandler;
    private UserInput userInput;
    private Scanner scanner;

    private STATUS status;

    private enum Menu {
        MAIN,
        ACTION
    }

    private Menu currentMenu = Menu.MAIN;

    public Application() {
        try {
            this.databaseManager = DatabaseManager.getInstance();
            this.commandHandler = new CommandHandler(this);
            this.scanner = new Scanner(System.in);
            this.userInput = new UserInput(this.scanner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSTATUS(STATUS status) {
        this.status = status;
    }

    public STATUS getStatus() {
        return this.status;
    }

    private void hello() {
        System.out.println("Bienvenue dans notre borne de réservation de bornes de recharge pour véhicules électriques.");
    }

    private void renderStartMenu() {
        String[] menu = {
                "Réserver une borne",
                "S'inscrire",
                "Se connecter",
                "Quitter"
        };

        printMenu(menu);
    }

    private void renderActionMenu() {
        String[] actions = {
                "Retour au menu principal",
                "Consultation de la disponibilité des bornes",
                "Réservation d'une borne",
                "Modification de la réservation",
                "Prolongation de la réservation",
                "Consultation des réservations existantes",
                "Consultation des transactions",
                "Paiement des frais",
                "Mise à jour des informations personnelles",
                "Désinscription",
                "Déconnexion",
                "Quitter l'application"
        };

        printMenu(actions);
    }

    public void run() {
        hello();
        while(true) {
            int min = 0;
            int max = 3;
            String menu = "MAIN";
            switch (currentMenu) {
                case MAIN:
                    renderStartMenu();
                    break;
                case ACTION:
                    renderActionMenu();
                    menu = "ACTION";
                    max = 11;
                    break;
            }
            commandHandler.handleCommand(userInput.getValidInt(min, max), menu);
        }
    }

    private void printMenu(String[] menu) {
        System.out.println("Veuillez choisir une option : (entrez le numéro correspondant)");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i) + ". " + menu[i]);
        }
    }
}
