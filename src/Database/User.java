package Database;

import java.io.Serializable;

public class User implements Serializable
{
    private String name;
    private String phoneNumber;
    private int age;
    private String username;
    private String password;
    
    public User(String name, String phoneNumber, int age, String username, String password)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public int getAge(){
        return this.age;
    }
    
    public void setAge(int age){
        this.age = age;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    } 

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    } 

    public String toString()
    {
        return 
        "Name         : " + this.name + "\n" +     
        "Phone Number : " + this.phoneNumber + "\n" + 
        "Age          : " + this.age + "\n\n";
    }
}