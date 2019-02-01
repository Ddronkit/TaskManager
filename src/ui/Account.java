package ui;

import exceptions.NullItemNameException;
import exceptions.TooMuchTaskException;
import gui.theme.ProgramTheme;
import gui.theme.Themes;
import module.TaskLists.ShoppingList;
import module.TaskLists.TaskList;
import storage.DataLoader;
import storage.DataSaver;
import tools.Printer;
import tools.TimeTool;
import tools.UserInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Account {
    private TaskList defaultList = new TaskList("To do list", this);
    private List<TaskList> listList = new ArrayList<>(Arrays.asList(defaultList));
    private DataSaver ds = new DataSaver();
    private DataLoader dl = new DataLoader();
    private static TimeTool timeTool = new TimeTool();
    private String keyInput;
    private String accountName;
    private Printer printer = new Printer();
    private UserInput input = new UserInput();
    private ProgramTheme accountTheme= new ProgramTheme();

    Scanner scanner = new Scanner(System.in);

    public Account(String UserName){
        accountName = UserName;
//        accountRun();
    }

    public void accountRun(){
        timeTool.getTime();
        loadList();
        operations();
        saveLists();
    }

    public String getAccountName(){return accountName;}


    public void addTaskList(String name){
        listList.add(new TaskList(name, this));
    }

    public void removeTaskList(TaskList tlist){
        listList.remove(tlist);
    }

    public TaskList getTaskList(String name){
        for(TaskList t : listList){
            if(t.name().equals(name)) {
                return t;
            }

        }
        return null;
    }


    public Themes getTheme(){
        return accountTheme.getTheme();
    }

    public void changeTheme(Themes theme){
        accountTheme.changeTheme(theme);
    }

    //REQUIRES: user input
    //MODIFIES: this
    //EFFECTS: create a new list with user specified listName and add it to the array of lists
    private void newTaskList(){
//        System.out.println("What's the name of the list?");
        TaskList nlist = null;
        try {
            nlist = new TaskList(input.getItemName(this), this);
        } catch (NullItemNameException e) {
            printer.nullItemName();
        }
        listList.add(nlist);
        nlist.run();

    }


    private void newShoppingList(){
//        System.out.println("What's the name of the list?");
        TaskList nlist = null;
        try {
            nlist = new ShoppingList(input.getItemName(this), this);
        } catch (NullItemNameException e) {
            printer.nullItemName();
        }
        listList.add(nlist);
        nlist.run();
    }


    //EFFECTS: print out the name of the lists currently in the account
    private void printLists(){
        for(TaskList tl :listList){
            System.out.println(tl.name());
        }
    }

    //EFFECTS: print out all the lists that are in the account
    private void printAllLists(){
        for(TaskList tl :listList){
            tl.printThisList();
        }
    }


    public List<TaskList> getTaskList(){
        return listList;
    }

    //EFFECTS: list of operations available for the user, if user input matches options, run operation. If not print error message.
    private void operations(){
        while(true) {
            printLists();
            System.out.println("Available function: add task list(atl), add shopping list(asl), run list (rl), print all lists(pal), quit(q)");
            keyInput = scanner.nextLine();
            if (keyInput.equals("atl") || keyInput.equals("add task list")) newTaskList();
            else if (keyInput.equals("asl") || keyInput.equals("add shopping list")) newShoppingList();
            else if (keyInput.equals("rl") || keyInput.equals("run list")) runList();
            else if (keyInput.equals("pal") || keyInput.equals("print all lists")) printAllLists();
            else if (keyInput.equals("quit") || keyInput.equals("q")) break;
            else System.out.println("That function you specified is currently not available.");
        }
    }


    //EFFECTS: run a list with user specified listName. If the specification doesn't exist, return an error message.
    private void runList(){
        System.out.println("Which list do you want to run?");
        keyInput = scanner.nextLine();
        for(TaskList t : listList){
            if(t.name().equals(keyInput)) {
                runningTaskList(t);
                return ;
            }
        }
        printer.itemNotExist(this);
    }


    private void extendTaskList(TaskList t){
        if (t instanceof ShoppingList){
            ShoppingList nSList = new ShoppingList(t.name()+"*", this);
            listList.add(nSList);
            nSList.run();
        }
        else if(t instanceof TaskList){
            TaskList nTList = new TaskList(t.name()+"*", this);
            listList.add(nTList);
            nTList.run();
        }

    }

    private void runningTaskList(TaskList t){
        try {
            t.run();
        }
        catch (TooMuchTaskException tmt){
            System.out.println("You have to much unfinished module.tasks in this list, would you like to create a new one? y/n");
            keyInput = scanner.nextLine();
            if(keyInput.equals("y")) extendTaskList(t);
            if(keyInput.equals("n"))runningTaskList(t);
        }
    }


    //EFFECTS: load the save related to the account
    private void loadList(){
        System.out.println("Do you want to load previous save? y/n");
        if(scanner.nextLine().equals("y")) {
            try {
                dl.loadData(listList,this);
            } catch (IOException e) {
                System.out.println("Error with loading data");
            }
        }
    }

    public void loadtheList(){
            try {
                dl.loadData(listList,this);
            } catch (IOException e) {
                System.out.println("Error with loading data");
            }

    }

    private void saveLists(){
        System.out.println("Do you want to save? y/n");
        if(scanner.nextLine().equals("y")) {
            try {
                ds.saveData(this);
            } catch (IOException i) {
                System.out.println("Error in saving data");
            }
        }
    }
    public void savetheLists(){
            try {
                ds.saveData(this);
            } catch (IOException i) {
                System.out.println("Error in saving data");
            }
    }

    public void update(List<TaskList> tlist){
        this.listList = tlist;
    }




}
