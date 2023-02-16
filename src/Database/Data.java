package Database;

import java.util.ArrayList;

public class Data 
{
    private static ArrayList<User>db = new ArrayList<>();

    public static void addData(User user)
    {
        db.add(user);
    }

    public static ArrayList<User> getDb()
    {
        return db;
    }

    public static void setDb(ArrayList<User> db)
    {
        
    }
}
