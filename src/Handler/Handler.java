package Handler;

import java.util.*;
import java.util.stream.IntStream;

import Database.*;

public class Handler 
{
    private static Scanner in = new Scanner(System.in);

    //Menu and Options
    public static void displayMenu()
    {
        Data.initializeDb();
        System.out.println("===================================================================================");
        System.out.println("                                 TOKO COBA                                         ");
        System.out.println("                             MADE BY DEVAN FERREL                                  ");
        System.out.println("===================================================================================");
        System.out.println("Welcome to Toko Coba CRUD!");
        optionsHandling();
        
    }

    private static void displayOptions()
    {
        System.out.println("1. Add User");
        System.out.println("2. Edit User");
        System.out.println("3. Remove User");
        System.out.println("4. Display All Users");
        System.out.println("5. Exit Program");
    }

    private static void optionsHandling()
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
                editUser();
                break;
            case '3':
                deleteUser();
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

    private static User getUserFromIndex(int index)
    {
        return Data.getDb().get(index);
    }

    private static int getIndexOfUser(String username)
    {
        ArrayList<User>db = Data.getDb();
        int sizeDb = db.size();
        IntStream stream = IntStream.range(0,sizeDb);
        return stream.filter(i -> db.get(i).getUsername().equals(username)).findFirst().orElse(-1);
    }

    private static boolean databaseEmpty()
    {
        return Data.getDb().size() == 0;
    }

    private static void throwLoading(String message) throws Exception
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
    private static void tableDisplay()
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

    private static void waitingHandler()
    {
        System.out.println("Press enter to continue");
        in.nextLine();
    }

    private static void exitProgramHandler()
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

    private static void displayHeaderOperation(String message)
    {
        System.out.println("===================================================================================");
        System.out.printf("%36s%s%36s\n","",message,"");
        System.out.println("===================================================================================");
    }
    
    //CRUD OPERATIONS
    private static void createUser()
    {
        displayHeaderOperation("CREATE USER");
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
        try {
            Data.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        optionsHandling();
    }

    private static void editUser()
    {
        String information[] = {"Name","Phone","Age","Username","Password"};
        char pick;
        int index,age;
        String name, phoneNum,password,username,choice;
        displayHeaderOperation("EDIT USER");
        System.out.println("Enter user's username");
        username = Input.usernameInputHandling(0);
        index = getIndexOfUser(username);
        while (index == -1)
        {
            System.out.println("User not found");
            System.out.println("Try again or back to menu");
            System.out.print("Choice : (y/n)");
            choice = in.nextLine().toLowerCase();
            if(choice.charAt(0) == 'y')
            {
                optionsHandling();
                return;
            }
            username = Input.usernameInputHandling(0);
            index = getIndexOfUser(username);
        }
        User user = Data.getDb().get(index);
        System.out.println("Update the information you need to update");
        System.out.println("Press n if you dont need to update specific information");

        for(String info : information)
        {
            System.out.printf("%-9s ? (y/n)   : ",info);
            pick = in.nextLine().toLowerCase().charAt(0);
            if(pick == 'n') continue;
            switch(info)
            {
                case "Name":
                    name = Input.nameInputHandling();
                    user.setName(name);
                    break;
                case "Phone":
                    phoneNum = Input.numberPhoneInputHandling();
                    user.setPhoneNumber(phoneNum);
                    break;
                case "Age":
                    age = Input.ageInputHandling();
                    user.setAge(age);
                    break;
                case "Username":
                    username = Input.usernameInputHandling(0);
                    user.setUsername(username);
                    break;
                case "Password":
                    password = Input.passwordInputHandling();
                    user.setPassword(password);
                    break;
            }
        }
        try {
            throwLoading("Updating user");
        } catch (Exception e) { 
            System.out.println(e.getMessage());
        }
        try {
            Data.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("User has been updated!");
        waitingHandler();
        optionsHandling();
    }

    private static void deleteUser()
    {
        String username, choice,password;
        boolean isExist = false, backMenu = false;
        displayHeaderOperation("DELETE USER");
        System.out.println("Enter user's username and password");
        username = Input.usernameInputHandling(0);
        isExist = checkUsername(username);
        while (!isExist && !backMenu) 
        {
            System.out.printf("User with username %s doesnt exist",username);
            System.out.println("Please try again ");
            System.out.print("or Back to menu ? (y/n)");
            choice = in.nextLine().toLowerCase();
            if(choice.charAt(0) == 'y') 
            {
                optionsHandling();
                return;
            }
            username = Input.usernameInputHandling(0);
        }
        User user = getUserFromIndex(getIndexOfUser(username));
        password = Input.passwordInputHandling();
        while (!user.getPassword().equals(password))
        {
            System.out.println("Password doesnt match");
            System.out.println("Please try again ");
            System.out.print("or Back to menu ? (y/n)");
            choice = in.nextLine().toLowerCase();
            if(choice.charAt(0) == 'y') 
            {
                optionsHandling();
                return;
            }
            password = Input.passwordInputHandling();
        }
        
        final String uname = username;
        Data.getDb().removeIf(u -> u.getUsername().equals(uname));
        try {
            throwLoading("Deleting user");
        } catch (Exception e) { 
            System.out.println(e.getMessage());
        }
        try {
            Data.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("User has been deleted");
        waitingHandler();
        optionsHandling();
    }
}
