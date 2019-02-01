package tools;

import exceptions.NullItemNameException;
import module.TaskLists.ShoppingList;
import module.TaskLists.TaskList;
import ui.Account;

import java.util.Scanner;

public class UserInput {
    String keyInput;
    Scanner scanner = new Scanner(System.in);

    public String getItemName(Object o) throws  NullItemNameException{
        if(o instanceof Account) System.out.println("What's the name of the list?");
        if(o instanceof ShoppingList) System.out.println("What do you need to buy?");
        else if(o instanceof TaskList)System.out.println("What do you need to do?");
        keyInput = scanner.nextLine().trim();
        if(keyInput.equals(""))throw new NullItemNameException();
        return keyInput;
    }

    public String getTaskTime(){
        System.out.println("Time? (00:00 - 23:59)");
        return scanner.nextLine().trim();
    }

    public String getTaskDate(){
        System.out.println("Date? (MM/DD)");
        return scanner.nextLine().trim();
    }

}
