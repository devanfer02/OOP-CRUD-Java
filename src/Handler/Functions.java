package Handler;

import java.util.*;
import java.util.stream.IntStream;

import Database.*;

public class Functions 
{
    private static Scanner in = new Scanner(System.in);

    public static void displayMenu()
    {
        Data.initializeDb();
        System.out.println("===================================================================================");
        System.out.println("                                 TOKO COBA                                         ");
        System.out.println("                             MADE BY DEVAN FERREL                                  ");
        System.out.println("===================================================================================");
        System.out.println("Welcome to Toko Coba CRUD!");
        Routes.optionsHandling();
        
    }

    protected static void displayOptions()
    {
        System.out.println("1. Add User");
        System.out.println("2. Edit User");
        System.out.println("3. Remove User");
        System.out.println("4. Display All Users");
        System.out.println("5. Exit Program");
    }
    
    protected static void displayHeaderOperation(String message)
    {
        System.out.println("===================================================================================");
        System.out.printf("%36s%s%36s\n","",message,"");
        System.out.println("===================================================================================");
    }

    protected static void updatingSerFile(String message) throws Exception
    {
        throwLoading(message);
        Data.writeFile();
    }

    protected static void usernameExist(String username)
    {
        do 
        {
            System.out.println("Username is exist already");
            System.out.println("Please enter new username");
            System.out.print("Username     : ");
            username = in.nextLine();
        } while (checkUsername(username));
    }

    protected static boolean checkUsername(String username)
    {   
        try {
            return Data.getDb()
            .stream().filter(user -> user.getUsername().equals(username)).findFirst().isPresent();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    protected static User getUserFromIndex(int index)
    {
        return Data.getDb().get(index);
    }

    protected static int getIndexOfUser(String username)
    {
        ArrayList<User>db = Data.getDb();
        int sizeDb = db.size();
        IntStream stream = IntStream.range(0,sizeDb);
        return stream.filter(i -> db.get(i).getUsername().equals(username)).findFirst().orElse(-1);
    }

    protected static boolean databaseEmpty()
    {
        return Data.getDb().size() == 0;
    }

    protected static void throwLoading(String message) throws Exception
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

    protected static void waitingHandler()
    {
        System.out.println("Press enter to continue");
        in.nextLine();
    }
}
