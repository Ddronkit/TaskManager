package ui;

import java.util.ArrayList;
import java.util.List;

public class TaskMask {
    private static List<Account> acList;

    public static void main(String[] args){
        defaultrun();

    }

    private static void defaultrun(){
        acList = new ArrayList<>();
        Account nAccount = new Account("User1");
        acList.add(nAccount);
    }
}
