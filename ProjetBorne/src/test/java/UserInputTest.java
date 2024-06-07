import static org.junit.jupiter.api.Assertions.*;

import app.UserInput;
import app.Validator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class UserInputTest {

    private final InputStream stdin = System.in;

    @BeforeEach
    public void setUp() {
        System.setIn(stdin);
    }

    @AfterEach
    public void tearDown() {
        System.setIn(stdin);
    }

    @Test
    public void testGetValidInput() {
        String testData = "John\nJane\n12345\ninvalidEmail\nvalid@email.com\n";
        System.setIn(new ByteArrayInputStream(testData.getBytes()));

        Scanner scanner = new Scanner(System.in);
        UserInput userInput = new UserInput(scanner);

        String name = userInput.getValidInput("Enter a name:", Validator::isValidName, "Invalid name format. Please try again.");
        assertEquals("John", name);

        String email = userInput.getValidInput("Enter an email:", Validator::isValidEmail, "Invalid email format. Please try again.");
        assertEquals("valid@email.com", email);
    }

    @Test
    public void testGetOptionalInput() {
        String testData = "12345\nvalid@email.com\n";
        System.setIn(new ByteArrayInputStream(testData.getBytes()));

        Scanner scanner = new Scanner(System.in);
        UserInput userInput = new UserInput(scanner);

        String address = userInput.getOptionalInput("Enter an address (optional):", Validator::isValidAddress, "Invalid address format. Please try again.");
        assertEquals("12345", address);

        String email = userInput.getOptionalInput("Enter an email:", Validator::isValidEmail, "Invalid email format. Please try again.");
        assertEquals("valid@email.com", email);
    }

    @Test
    public void testGetOptionalInputEmpty() {
        String testData = "\nvalid@email.com\n";
        System.setIn(new ByteArrayInputStream(testData.getBytes()));

        Scanner scanner = new Scanner(System.in);
        UserInput userInput = new UserInput(scanner);

        String address = userInput.getOptionalInput("Enter an address (optional):", Validator::isValidAddress, "Invalid address format. Please try again.");
        assertEquals("", address);

        String email = userInput.getOptionalInput("Enter an email:", Validator::isValidEmail, "Invalid email format. Please try again.");
        assertEquals("valid@email.com", email);
    }

    @Test
    public void testGetValidInt() {
        String testData = "5\n";
        System.setIn(new ByteArrayInputStream(testData.getBytes()));

        Scanner scanner = new Scanner(System.in);
        UserInput userInput = new UserInput(scanner);

        int number = userInput.getValidInt(0, 10);
        assertEquals(5, number);
    }

    @Test
    public void testGetInvalidInt() {
        String testData = "invalid\n5\n";
        System.setIn(new ByteArrayInputStream(testData.getBytes()));

        Scanner scanner = new Scanner(System.in);
        UserInput userInput = new UserInput(scanner);

        int number = userInput.getValidInt(0, 10);
        assertEquals(5, number);
    }
}
