import java.util.Scanner;

public class PasswordTool {
    public static void main(String[] args) {
        //Create a scanner
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        
    while (!valid) {
        valid = true;
        int score = 0;
        String message = "";
        //Input from user
        int choice;

        do {
            System.out.println("1. Enter your own password");
            System.out.println("2. Generate a password");
            System.out.print("Choose an option: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine(); // clear buffer

                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid option. Please enter 1 or 2.\n");
                }

            } else {
                System.out.println("Invalid input. Please enter a number.\n");
                input.nextLine(); // clear bad input
                choice = -1; // force loop to continue
            }

        } while (choice != 1 && choice != 2);

        System.out.println();
        
        String password;

        if (choice == 2) {
            char again;

        do {
            int length;

            System.out.print("Enter desired password length (minimum 12): ");

            while (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
                System.out.print("Enter desired password length (minimum 12): ");
            }

            length = input.nextInt();
            input.nextLine();

            while (length < 12) {
                System.out.print("Length must be at least 12. Try again: ");
                length = input.nextInt();
                input.nextLine();
            }

            password = generatePassword(length);
            System.out.println("Generated password: " + password);
            System.out.println("Password strength: Strong");
            System.out.println("Password is valid\n");

            System.out.print("Generate another? (y/n): ");
            String response = input.nextLine().toLowerCase();

            if (response.length() > 0) {
                again = response.charAt(0);
            } else {
                again = 'n'; // default to stop
            }
            System.out.println();

        } while (again == 'y');
        
        continue;
        
        } else {
            if (System.console() != null) {
                password = new String(System.console().readPassword("Enter a password: "));
            } else {
                System.out.print("Enter a password: ");
                password = input.nextLine();
            }
            System.out.println();
        }

        
        //Call method to determine if password input is valid length
        if (!isValidLength(password)) {
            message = message + "- Password requires minimum of 12 characters\n";
            valid = false;
        } else {
            score++;
        }
        
        //Call method to determine if password input contains uppercase letter
        if (!hasUppercase(password)) {
            message = message + "- Password requires one uppercase letter\n";
            valid = false;
        } else {
            score++;
        }
        
        //Call method to determine if password input contains one number
        if (!containsNumber(password)) {
            message = message + "- Password requires at least one number\n";
            valid = false;
        } else {
            score++;
        }
        
        //Call method to determine if password input contains special character
        if (!containsSpecialCharacter(password)) {
            message = message + "- Password requires one special character\n";
            valid = false;
        } else {
            score++;
        }
        
        //Call method to determine if password input contains lowercase letter
        if (!containsLowercase(password)) {
            message = message + "- Password requires one lowercase letter\n";
            valid = false;
        } else {
            score++;
        }
        
        //Call method to determine if password is a common one
        if (isCommonPassword(password)) {
            message = message + "- Password is too common\n";
            valid = false;
        } else {
            score++;
        }
        
        if (!valid) {
            System.out.println("Invalid password:");
            System.out.println(message);
        }
                
        //Calculate score for password
        if (score <= 2) {
            System.out.println("Password strength: Weak");
        } else if (score <= 4) {
            System.out.println("Password strength: Medium");
        } else {
            System.out.println("Password strength: Strong");
        }
        
        System.out.println();
        
        //Check if all required conditions are met to verify valid password
        if (valid) {
            System.out.println("Password is valid");
        }
        
      }
    }
    
    //Validate password length is 12 characters
    public static boolean isValidLength(String password) {
            return password.length() >= 12;
        }     
    
    //Validate password contains uppercase letter
    public static boolean hasUppercase(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                return true;
            } 
        }
        return false;
    }
    
    //Validate password contains one number character
    public static boolean containsNumber(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }
    
    //Validate password contains one special character
    public static boolean containsSpecialCharacter(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if ((!Character.isLetter(ch)) && (!Character.isDigit(ch))) {
                return true;
            }
        }
        return false;
    }
    
    //Validate password contains one lowercase letter
    public static boolean containsLowercase(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                return true;
            }
        }
        return false;
    }
    
    //Validate password is not a common one
public static boolean isCommonPassword(String password) {
    String[] commonPasswords = {
        "password", "password123", "123456", "12345678", "123456789",
        "qwerty", "qwerty123", "abc123", "welcome", "admin", "admin123",
        "letmein", "iloveyou", "monkey", "dragon", "football",
        "baseball", "login", "starwars", "hello", "freedom",
        "whatever", "trustno1", "sunshine", "master", "shadow",
        "ashley", "bailey", "passw0rd", "superman", "pokemon",
        "charlie", "donald", "michael", "jessica", "killer",
        "hockey", "soccer", "ginger", "summer", "winter",
        "spring", "autumn", "secret", "adminadmin", "welcome123"
    };

    String lower = password.toLowerCase();

    for (int i = 0; i < commonPasswords.length; i++) {
        String common = commonPasswords[i];

        // exact match (clear intent)
        if (lower.equals(common)) {
            return true;
        }

        // starts or ends with common password
        if (lower.startsWith(common) || lower.endsWith(common)) {
            return true;
        }
    }

    return false;
}
    
    //Generate password
    public static String generatePassword(int length) {
        String password = "";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String specialCharacter = "!@#$%^&*";
        
        //Generate random uppercase
        int index = (int)(Math.random() * upperCase.length());
        char character = upperCase.charAt(index);
        password = password + character;
        
        //Generate random lowercase
        int index2 = (int)(Math.random() * lowerCase.length());
        char character2 = lowerCase.charAt(index2);
        password = password + character2;
        
        //Generate random number
        int index3 = (int)(Math.random() * number.length());
        char character3 = number.charAt(index3);
        password = password + character3;
        
        //Generate random special character
        int index4 = (int)(Math.random() * specialCharacter.length());
        char character4 = specialCharacter.charAt(index4);
        password = password + character4;
        
        //Pool together all character types
        String allCharacters = upperCase + lowerCase + number + specialCharacter;
        
        //Fill in remaining characters until 12 total is reached
        while (password.length() < length) {
            int index5 = (int)(Math.random() * allCharacters.length());
            char character5 = allCharacters.charAt(index5);
            password = password + character5;
        }
        // shuffle characters
        char[] chars = password.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int randomIndex = (int)(Math.random() * chars.length);

            char temp = chars[i];
            chars[i] = chars[randomIndex];
            chars[randomIndex] = temp;
        }

        password = new String(chars);
        return password;
    }
}
