package app;

import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidName(String name) {
        String nameRegex = "^[\\p{L} .'-]+$"; // Permet les lettres, les espaces, les points, les apostrophes et les tirets
        Pattern pat = Pattern.compile(nameRegex);
        return name != null && pat.matcher(name).matches();
    }

    public static boolean isValidAddress(String address) {
        return address != null && address.length() >= 5;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
    }

    public static boolean isValidCreditCard(String card) {
        String cardRegex = "^(?:4[0-9]{12}(?:[0-9]{3})?" + // Visa
            "|5[1-5][0-9]{14}" + // MasterCard
            "|3[47][0-9]{13}" + // American Express
            "|3(?:0[0-5]|[68][0-9])[0-9]{11}" + // Diners Club
            "|6(?:011|5[0-9]{2})[0-9]{12}" + // Discover
            "|(?:2131|1800|35\\d{3})\\d{11})$"; // JCB
        Pattern pat = Pattern.compile(cardRegex);
        return card != null && pat.matcher(card).matches();
    }

    public static boolean isValidLicensePlate(String plate) {
        String plateRegex = "^[A-Z0-9-]{1,8}$"; // Simple validation for license plates
        Pattern pat = Pattern.compile(plateRegex);
        return plate == null || plate.isEmpty() || pat.matcher(plate).matches();
    }

    public static boolean isValidMobileNumber(String number) {
        String numberRegex = "^\\d{10}$"; // Format : 10 chiffres
        Pattern pat = Pattern.compile(numberRegex);
        return number != null && pat.matcher(number).matches();
    }
}