package Database;

import java.util.ArrayList;
import java.io.*;

public class Data 
{
    private static ArrayList<User>db = new ArrayList<>();

    public static void initializeDb()
    {
        try {
            db = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addData(User user)
    {
        db.add(user);
    }

    public static ArrayList<User> getDb()
    {
        return db;
    }

    public static void writeFile() throws Exception
    {
        FileOutputStream writeFile = new FileOutputStream("userdata.ser");
        ObjectOutputStream writeStream = new ObjectOutputStream(writeFile);
            
        writeStream.writeObject(db);
        writeStream.flush();
        writeFile.close();
    }    

    public static ArrayList<User> readFile() throws Exception
    {
        FileInputStream readData = new FileInputStream("userdata.ser");
        ObjectInputStream readStream = new ObjectInputStream(readData);
 
        @SuppressWarnings("unchecked")
        ArrayList<User>db  = (ArrayList<User>) readStream.readObject();
        readStream.close();
        return db;
    }
}
