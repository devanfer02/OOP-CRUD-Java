package Handler;

public class Routes 
{
    protected static void optionsHandling()
    {
        System.out.println("\nChoose operation you want to do");
        Functions.displayOptions();
        char choice = Input.choiceInputHandling();
        switch(choice)
        {
            case '1':
                Handler.createUser();
                break;
            case '2':
                Handler.editUser();
                break;
            case '3':
                Handler.deleteUser();
                break;
            case '4':
                Handler.tableDisplay();
            case '5':
                Handler.exitProgramHandler();
        }  
    }
}   
