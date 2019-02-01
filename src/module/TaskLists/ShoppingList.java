package module.TaskLists;

import exceptions.NullItemNameException;
import exceptions.TooMuchTaskException;
import module.tasks.NormalTask;
import ui.Account;

public class ShoppingList extends TaskList {
    public ShoppingList(String listName, Account ac) {
        super(listName, ac);
    }


    protected void addTask(String type)throws TooMuchTaskException{
        if(getNumOfTask()==listLimit)throw new TooMuchTaskException();
        String ToDo;
        try {
            ToDo = inputToDo();
        } catch (NullItemNameException ntn) {
            printer.nullItemName();
            return ;
        }
        if(ToDo.equals("cancel") || ToDo.equals("quit"));
        else{
            if(type.equals("N")) {tasks.put(ToDo, new NormalTask(ToDo,"","","", this));}
        }
    }

//    @Override
//    protected String inputToDo() {
//        System.out.println("What do you need to buy?");
//        return scanner.nextLine();
//
//    }


    protected void operationList(){
        while(true) {
            printer.functionHeading(this);
            keyInput = scanner.nextLine();
            if (keyInput.equals("add") || keyInput.equals("add stuff")) addTask("N");
            else if (keyInput.equals("pt") || keyInput.equals("print")) printThisList();
//            else if (keyInput.equals("gt") || keyInput.equals("got stuff")) finishedTask();
            else if (keyInput.equals("psg") || keyInput.equals("print stuff got"))printFinished();
            else if (keyInput.equals("quit") || keyInput.equals("q")) break;
            else printer.funcUnavailable();
        }
    }

//    @Override
//    protected void printUndoneHeading(){
//        System.out.printf("%-10s%s%n","","You still need to get:");
//    }
//
//    @Override
//    protected void printDoneHeading(){
//        System.out.printf("%-10s%s%n","","Things you've got: ");
//    }


}
