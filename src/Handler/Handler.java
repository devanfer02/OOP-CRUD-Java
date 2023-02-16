package Handler;

import java.util.*;
import Database.*;

public class Handler 
{
    private static Scanner in = new Scanner(System.in);
    
    //Input Handling
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
