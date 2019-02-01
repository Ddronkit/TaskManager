//package ui;
//
//import module.TaskLists.ShoppingList;
//import module.TaskLists.TaskList;
//import storage.DataLoader;
//import storage.DataSaver;
//import module.tasks.Task;
//import tools.TimeTool;
//
//import java.util.*;
//
//public class User {
//    private Map<String, List<Task>> dataTree = new HashMap<>();
//    private DataSaver ds = new DataSaver();
//    private DataLoader dl = new DataLoader();
//    private static TimeTool timeTool = new TimeTool();
//    private String keyInput;
//    private String userName;
//    private String stage = "";
//
//    Scanner scanner = new Scanner(System.in);
//
//
//    public User(String UserName){
//        userName = UserName;
//        dataTree.put("To Do List", new ArrayList<>());
//    }
//
//    //REQUIRES: user input
//    //MODIFIES: this
//    //EFFECTS: create a new list with user specified listName and add it to the array of lists
//    public void newTaskList(){
//        System.out.println("What's the name of the list?");
//        TaskList nlist = new TaskList(scanner.nextLine().trim(), this);
//        dataTree.put(nlist, new ArrayList<>());
//
//    }
//
//    private void newShoppingList(){
//        System.out.println("What's the name of the list?");
//        TaskList nlist = new ShoppingList(scanner.nextLine(), this);
//        dataTree.put(nlist, new ArrayList<>());
//    }
//
//    //EFFECTS: print out the name of the lists currently in the account
//    private void printLists(){
//        for(TaskList tl :dataTree.keySet()){
//            System.out.println(tl.name());
//        }
//    }
//
//    //EFFECTS: print out all the lists that are in the account
//    private void printAllLists(){
//        for(TaskList tl :dataTree.keySet()){
//            tl.printThisList();
//        }
//    }
//
//
//    private void runList(){
//        System.out.println("Which list do you want to run?");
//        keyInput = scanner.nextLine();
//        dataTree.get()
//    }
//
//    //EFFECTS: list of operations available for the user, if user input matches options, run operation. If not print error message.
//    private void operations(){
//        while(true) {
//            printLists();
//            System.out.println("Available function: add task list(al), add shopping list(asl), run list (rl), print all lists(pal), quit(q)");
//            keyInput = scanner.nextLine();
//            if (keyInput.equals("atl") || keyInput.equals("add task list")) newTaskList();
//            else if (keyInput.equals("asl") || keyInput.equals("add shopping list")) newShoppingList();
//            else if (keyInput.equals("rl") || keyInput.equals("run list")) runList();
//            else if (keyInput.equals("pal") || keyInput.equals("print all lists")) printAllLists();
//            else if (keyInput.equals("quit") || keyInput.equals("q")) break;
//            else System.out.println("That function you specified is currently not available.");
//        }
//    }
//
//
//}
