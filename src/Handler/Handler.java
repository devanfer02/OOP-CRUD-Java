package Handler;

import java.util.*;
import Database.*;

public class Handler 
{
    private static Scanner in = new Scanner(System.in);

    //Menu and Options
    public static void displayMenu()
    {
        System.out.println("===================================================================================");
        System.out.println("                                 TOKO COBA                                         ");
        System.out.println("                             MADE BY DEVAN FERREL                                  ");
        System.out.println("===================================================================================");
        System.out.println("Welcome to Toko Coba CRUD!");
        optionsHandling();
        
    }

    public static void displayOptions()
    {
        System.out.println("1. Add User");
        System.out.println("2. Edit User");
        System.out.println("3. Remove User");
        System.out.println("4. Display All Users");
        System.out.println("5. Exit Program");
    }

    public static void optionsHandling()
    {
        System.out.println("\nChoose operation you want to do");
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
                tableDisplay();
            case '5':
                exitProgramHandler();
        }
        
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

    public static void throwLoading(String message) throws Exception
    {
        System.out.print(message);
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(700);
        System.out.print(".");
        Thread.sleep(900);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        System.out.print("\n"); 
    }

    //Display Handling
    public static void tableDisplay()
    {
        if(databaseEmpty())
        {
            System.out.println("There's no user in database");
            waitingHandler();
            optionsHandling();
            return;
        }
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("                               DISPLAY USER                                        ");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("|                Name               |        Phone Number        |       Age      |");
        System.out.println("-----------------------------------------------------------------------------------");
        for(User user : Data.getDb())
        {
            System.out.printf("| %-33s | %-26s | %-14d |\n",user.getName(),user.getPhoneNumber(),user.getAge());
            System.out.println("-----------------------------------------------------------------------------------");
        }
        waitingHandler();
        optionsHandling();
    }

    public static void waitingHandler()
    {
        System.out.println("Press enter to continue");
        in.nextLine();
    }

    public static void exitProgramHandler()
    {
        System.out.println("Thank you for using our program");
        System.out.println("Please leave your review");
        String review = in.nextLine();
        System.out.println("Your review is : ");
        System.out.print("[");
        System.out.print(review);
        System.out.print("]\n");
        System.out.println("Went to void this message has :)");
        System.exit(0);
    }

    
    //CRUD OPERATIONS
    public static void createUser()
    {
        System.out.println("===================================================================================");
        System.out.println("                                CREATE USER                                        ");
        System.out.println("===================================================================================");
        String name = Input.nameInputHandling(); 
        String phoneNum = Input.numberPhoneInputHandling(); 
        int age = Input.ageInputHandling(); 
        String username = Input.usernameInputHandling();
        String password = Input.passwordInputHandling();
        System.out.print("\n");
        try {
            throwLoading("Creating user");
        } catch (Exception e) { 
            System.out.println(e.getMessage());
        }
        Data.addData(new User(name,phoneNum,age,username,password));
        System.out.println("User has been created!");
        waitingHandler();
        optionsHandling();
    }
}
