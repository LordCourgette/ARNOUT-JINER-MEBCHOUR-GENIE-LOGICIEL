import java.util.Scanner;

public class Application {

    private UserInput userInput;
    private Scanner scanner;

    public Application() {
        this.scanner = new Scanner(System.in);
        this.userInput = new UserInput(scanner);
        connexion();
    }

    public void connexion() {
        System.out.println("\nBonjour, choisir ce que vous voulez faire.");
        System.out.println("1) Tapez 1 pour réserver");
        System.out.println("2) Tapez 2 pour vous inscrire");
        String input = scanner.next();
        if (input.equals("1")) {
            identification();
        } else if (input.equals("2")) {
            inscription();
        } else {
            System.out.println("Vous n'avez pas choisi une option valide, veuillez réessayer");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            connexion();
        }
    }

    public void identification() {
        // Logique pour l'identification
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
        DatabaseManager.insertClient(client);

        System.out.println("Inscription réussie !");

        connexion();
    }

    public static void main(String[] args) {
        new Application();
    }
}
