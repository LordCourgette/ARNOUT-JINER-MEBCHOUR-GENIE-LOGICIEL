import java.util.Scanner;

public class Application {

    private Scanner scanner;

    public Application(){
        scanner = new Scanner(System.in);
        connexion();
    }



    public void connexion(){

        System.out.println("\nBonjour, choisir ce que vous voulez faire.");
        System.out.println("1) Tapez 1 pour réserver");
        System.out.println("2) Tapez 2 pour vous inscrire");
        String input = scanner.next();
        if(input.equals("1")){
            identification();
        } else if(input.equals("2")) {
            inscription();
        } else {
            System.out.println("Vous n'avez pas choisi une option valide, veuillez réessayez");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            connexion();
        }

    }

    public void identification(){

    }

    public void inscription(){
        System.out.println("Vous avez choisi l'inscription\n");

        System.out.println("Veuillez saisir votre nom");
        String nom = scanner.next();
        System.out.println("Veuillez saisir votre prénom");
        String prenom = scanner.next();
        System.out.println("Veuillez saisir votre adresse");
        String adresse = scanner.next();
        System.out.println("Veuillez saisir votre adresse mail");
        String mail = scanner.next();
        System.out.println("Veuillez saisir votre numéro de carte de crédit");
        String carte = scanner.next();

    }




}
