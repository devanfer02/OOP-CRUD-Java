package Handler;

import java.util.*;
import Database.*;

public class Handler 
{
    private static Scanner in = new Scanner(System.in);

    //Menu and Options
    public static void displayMenu()
    {
        System.out.println("Welcome to Toko Coba CRUD!");
        System.out.println("Choose operation you want to do");
        displayOptions();
        char choice = Input.choiceInputHandling();
        switch(choice)
        {
            case '1':
                createUser();
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                if(databaseEmpty())
                {
                    System.out.println("There's no user in database");
                }
                else
                {
                    tableDisplay();
                }
        }
        
    }

    public static void displayOptions()
    {
        System.out.println("1. Add User");
        System.out.println("2. Edit User");
        System.out.println("3. Remove User");
        System.out.println("4. Display All Users");
    }
    

    public static void createUser()
    {
        String name = Input.nameInputHandling(); 
        String phoneNum = Input.numberPhoneInputHandling(); 
        int age = Input.ageInputHandling(); 
        String username = Input.usernameInputHandling();
        String password = Input.passwordInputHandling();
        System.out.print("\n");
        Data.addData(new User(name,phoneNum,age,username,password));
    }

    public static void usernameExist(String username)
    {
        do 
        {
            System.out.println("Username is exist already");
            System.out.println("Please enter new username");
            System.out.print("Username     : ");
            username = in.nextLine();
        } while (checkUsername(username));
    }

    public static boolean checkUsername(String username)
    {   
        try {
            return Data.getDb()
            .stream().filter(user -> user.getUsername().equals(username)).findFirst().isPresent();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }

    public static boolean databaseEmpty()
    {
        return Data.getDb().size() == 0;
    }

    //Display Handling
    public static void tableDisplay()
    {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("|                Name               |        Phone Number        |       Age      |");
        System.out.println("-----------------------------------------------------------------------------------");
        for(User user : Data.getDb())
        {
            System.out.printf("| %-33s | %-26s | %-14d |\n",user.getName(),user.getPhoneNumber(),user.getAge());
            System.out.println("-----------------------------------------------------------------------------------");
        }
    }
}
