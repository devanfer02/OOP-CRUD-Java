package Handler;

import java.util.*;

import Database.*;

public class Handler 
{
    private static Scanner in = new Scanner(System.in);
    
    protected static void createUser() 
    {
        Functions.displayHeaderOperation("CREATE USER");
        String name = Input.nameInputHandling(); 
        String phoneNum = Input.numberPhoneInputHandling(); 
        int age = Input.ageInputHandling(); 
        String username = Input.usernameInputHandling();
        String password = Input.passwordInputHandling();
        Data.addData(new User(name,phoneNum,age,username,password));
        try {
            Functions.updatingSerFile("Creating user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("User has been created!");
        Functions.waitingHandler();
        Routes.optionsHandling();
    }

    protected static void editUser()
    {
        String information[] = {"Name","Phone","Age","Username","Password"};
        char pick;
        int index,age;
        String name, phoneNum,password,username,choice;
        Functions.displayHeaderOperation("EDIT USER");
        System.out.println("Enter user's username");
        username = Input.usernameInputHandling(0);
        index = Functions.getIndexOfUser(username);
        while (index == -1)
        {
            System.out.println("User not found");
            System.out.println("Try again or back to menu");
            System.out.print("Choice : (y/n)");
            choice = in.nextLine().toLowerCase();
            if(choice.charAt(0) == 'y')
            {
             Routes.   optionsHandling();
                return;
            }
            username = Input.usernameInputHandling(0);
            index = Functions.getIndexOfUser(username);
        }
        User user = Data.getDb().get(index);
        System.out.println("Update the information you need to update");
        System.out.println("Press n if you dont need to update specific information");

        for(String info : information)
        {
            System.out.printf("%-9s ? (y/n)   : ",info);
            pick = in.nextLine().toLowerCase().charAt(0);
            if(pick == 'n' || pick != 'y') continue;
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
            Functions.updatingSerFile("Updating user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("User has been updated!");
        Functions.waitingHandler();
        Routes.optionsHandling();
    }

    protected static void deleteUser()
    {
        String username, choice,password;
        boolean isExist = false, backMenu = false;
        Functions.displayHeaderOperation("DELETE USER");
        System.out.println("Enter user's username and password");
        username = Input.usernameInputHandling(0);
        isExist = Functions.checkUsername(username);
        while (!isExist && !backMenu) 
        {
            System.out.printf("User with username %s doesnt exist\n",username);
            System.out.println("Please try again ");
            System.out.print("or Back to menu ? (y/n) : ");
            choice = in.nextLine().toLowerCase();
            if(choice.charAt(0) == 'y') 
            {
                Routes.optionsHandling();
                return;
            }
            username = Input.usernameInputHandling(0);
        }
        User user = Functions.getUserFromIndex(Functions.getIndexOfUser(username));
        password = Input.passwordInputHandling();
        while (!user.getPassword().equals(password))
        {
            System.out.println("Password doesnt match");
            System.out.println("Please try again ");
            System.out.print("or Back to menu ? (y/n) : ");
            choice = in.nextLine().toLowerCase();
            if(choice.charAt(0) == 'y') 
            {
             Routes.optionsHandling();
                return;
            }
            password = Input.passwordInputHandling();
        }
        
        final String uname = username;
        Data.getDb().removeIf(u -> u.getUsername().equals(uname));
        try {
            Functions.updatingSerFile("Deleting user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("User has been deleted");
        Functions.waitingHandler();
        Routes.optionsHandling();
    }

    protected static void tableDisplay()
    {
        if(Functions.databaseEmpty())
        {
            System.out.println("There's no user in database");
            Functions.waitingHandler();
            Routes.optionsHandling();
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
        Functions.waitingHandler();
        Routes.optionsHandling();
    }

    protected static void exitProgramHandler()
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
}
