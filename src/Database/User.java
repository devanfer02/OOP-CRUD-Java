package Database;

public class User
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

    //Getter-Setter
    public String getName(){
        return this.name;
    }
    
    void setName(String name){
        this.name = name;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public int getAge(){
        return this.age;
    }
    
    void setAge(int age){
        this.age = age;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String toString()
    {
        return 
        "Name         : " + this.name + "\n" +     
        "Phone Number : " + this.phoneNumber + "\n" + 
        "Age          : " + this.age + "\n\n";
    }
}