package Handler;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Input
{
    static Scanner in = new Scanner(System.in);

    //Registration Handling
    protected static String nameInputHandling()
    {
        System.out.print("Name         : ");
        String name = in.nextLine();
        String regex = ".*\\d.*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        boolean notValid = matcher.matches();
        while(notValid) 
        {
            System.out.print("\n");
            System.out.println("Please enter valid name");
            System.out.println("Valid name only contains letter");
            System.out.print("Name         : ");
            name = in.nextLine();
            matcher = pattern.matcher(name);
            notValid = matcher.matches();
        }
        return name;
    }

    protected static String numberPhoneInputHandling()
    {
        String phoneNum =  "";
        boolean isPhone = false;
        do {
            try {
                System.out.print("Phone Number : ");
                phoneNum = in.next();
                if(phoneNum.length() < 10 || phoneNum.length() > 13)
                {
                    throw new PhoneNumberException("Please enter valid number");
                }
                isPhone = true;
            } catch (PhoneNumberException pne) {
                System.out.print("\n");
                System.out.println(pne.getMessage());
                System.out.println("Valid number only contains between 10 and 13 digits");
            }
        } while(!isPhone);
        return phoneNum;
        
    }

    protected static int ageInputHandling()
    {
        int age = -1;
        boolean match = false;
        do {
            try {
                System.out.print("Age          : ");
                age = in.nextInt(); 
                match = true;
                in.nextLine();
            } catch (InputMismatchException ime) {
                System.out.print("\n");
                System.out.println("Age must be a number");
                in.next();
            }
        } while(!match);
        if (age < 0) System.out.println("Oi baby, go back to your mother's womb");
        if (age > 300) System.out.println("Damn gramps, still alive"); 
        return age;
    }

    protected static String usernameInputHandling()
    {
        String username = "";
        System.out.print("Username     : ");
        username = in.nextLine();
        if (Handler.checkUsername(username)) 
        {
            Handler.usernameExist(username);
        }
        return username;
    }

    protected static String usernameInputHandling(int status)
    {
        String username = "";
        System.out.print("Username     : ");
        username = in.nextLine();
        return username;
    }

    protected static String passwordInputHandling()
    {
        String regex = ".*\\d.*";
        Pattern pattern = Pattern.compile(regex);
        
        String password = "";
        System.out.print("Password     : ");
        password = in.nextLine();
        Matcher matcher = pattern.matcher(password);
        boolean isValid = matcher.matches();
        boolean hasMinimum = password.length() >= 8;
        boolean hasSpaceChar = password.indexOf(' ') != -1;
        while (!hasMinimum || !isValid || hasSpaceChar)
        {
            System.out.print("\n");
            if (!hasMinimum) System.out.println("Minimum password length is 8");
            if (!isValid) System.out.println("Password must contain atleast 1 number");
            if (hasSpaceChar) System.out.println("Password cannot contain space character");
            System.out.println("Please enter new password");
            System.out.print("Password     : ");
            password = in.nextLine();
            matcher = pattern.matcher(password);
            isValid = matcher.matches();
            hasMinimum = password.length() >= 8;
            hasSpaceChar = password.indexOf(' ') != -1;
        }
        return password;
    }

    //Choice Handling
    protected static char choiceInputHandling()
    {
        char choice = ' ';
        System.out.print("Select your choice : ");
        try {
            choice = in.next().charAt(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Congratulations! You found a bug!");
            System.out.println("Please report this to admin");
            System.exit(0);
        }
        int numberValue = Character.getNumericValue(choice);
        while (numberValue < 1 || numberValue > 5) 
        {
            System.out.print("\n");
            System.out.println("Please enter valid choice");
            System.out.print("Select your choice : ");
            choice = in.next().charAt(0);
            numberValue = Character.getNumericValue(choice);
        }
        in.nextLine();
        return choice;
    }
}
