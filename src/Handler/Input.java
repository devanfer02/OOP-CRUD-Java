package Handler;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Input
{
    static Scanner in = new Scanner(System.in);

    public static String nameInputHandling()
    {
        System.out.print("Name         : ");
        String name = in.nextLine();
        String regex = ".*\\d.*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        boolean notValid = matcher.matches();
        while(notValid) 
        {
            System.out.println("Please enter valid name");
            System.out.println("Valid name only contains letter");
            System.out.print("Name         : ");
            name = in.nextLine();
            matcher = pattern.matcher(name);
            notValid = matcher.matches();
        }
        return name;
    }

    public static String numberPhoneInputHandling()
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
                System.out.println(pne.getMessage());
                System.out.println("Valid number only contains between 10 and 13 digits");
            }
        } while(!isPhone);
        return phoneNum;
        
    }

    public static int ageInputHandling()
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
                System.out.println("Age must be a number");
                in.next();
            }
            
        } while(!match);
        return age;
    }

    public static String usernameInputHandling()
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

    public static String passwordInputHandling()
    {
        String password = "";
        System.out.print("Password     : ");
        password = in.next();
        while (password.length() < 8)
        {
            System.out.println("Minimum password length is 8");
            System.out.println("Please use new password");
            System.out.print("Password     : ");
            password = in.next();
        }
        in.nextLine();
        return password;
    }
}
