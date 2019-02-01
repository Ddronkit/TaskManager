package tools;

import module.TaskLists.ShoppingList;
import module.TaskLists.TaskList;
import module.tasks.NormalTask;
import module.tasks.Task;
import ui.Account;

import java.util.Map;

public class Printer {
    protected String format = "%-10s%s%n";


    public void undoneHeading(Object o){
        if(o instanceof ShoppingList)System.out.printf(format,"","You still need to get:");
        else if(o instanceof TaskList)System.out.printf(format,"","You currently have: ");
    }


    public void doneHeading(Object o){
        if(o instanceof ShoppingList)System.out.printf(format,"","Things you've got: ");
        else if(o instanceof TaskList)System.out.printf(format,"","You have finished: ");
    }

    public void functionHeading(Object o){
        if(o instanceof ShoppingList)System.out.println("Available function: print(pt), add stuff(add), got stuff(gt), print stuff got(psg), quit(q)");
        else if(o instanceof TaskList)System.out.println("Available function: print tasks(pt), add task(at), add personal task(apt), finish task(ft), print finished tasks(pft), quit(q)");
        if(o instanceof Account)System.out.println("Available function: add task list(al), add shopping list(asl), run list (rl), print all lists(pal), quit(q)");
    }

    public void emptylist(Object o){
        if(o instanceof TaskList)System.out.println("You currently have nothing in the list.");
    }

    public void itemNotExist(Object o){
        if(o instanceof TaskList)System.out.println("The task you specified does not exist.");
        if(o instanceof Account)System.out.println("The list you specify doesn't exist");
    }

    public void taskAlreadyFinished(){System.out.println("You have already finished that task.");}

    public void nullItemName(){System.out.println("No name is specified");}

    public void funcUnavailable(){System.out.println("That function you specified is currently not available.");}

    public void taskPrint(Task t) {
        String type;
        if(t instanceof NormalTask) type = "";
        else type = t.getType();
        if(t.getTaskTime().equals("")){
            if(t.getDateString().equals(""))System.out.printf(format,type,t.getTaskName());
            else System.out.printf(format,type, t.getTaskName() + " on " + t.getDateString());}
        else if (t.getDateString().equals(""))System.out.printf(format,type, t.getTaskName() + " at " + t.getTaskTime());
        else System.out.printf(format,type, t.getTaskName() + " at " + t.getTaskTime() + " on " + t.getDateString());
    }

    //EFFECTS: print out the list. if list have nothing print message.
    public void printTasks(TaskList tl){
        System.out.println(tl.name()+":");
        if(tl.getTasks2().isEmpty()) emptylist(tl);
        else {
            undoneHeading(tl);
            sortPrint("unfinished", tl.getTasks2());
        }
        System.out.println("\n");
    }

    //EFFECTS: print out the finished module.tasks. If nothing then print message.
    public void printFinished(TaskList tl){
        System.out.println(tl.name()+":");
        if(tl.getTasks2().isEmpty()) emptylist(tl);
        else {
            doneHeading(tl);
            sortPrint("finished", tl.getTasks2());
        }
    }

    private void sortPrint(String type, Map<String, Task> tasks){
        int priority = 2;
        while(priority>=0){
            for(String key: tasks.keySet()){
                Task t = tasks.get(key);
                if(t.getPriority()==priority && !t.completed()&& type.equals("unfinished"))t.print();
                if(t.getPriority()==priority && t.completed()&& type.equals("finished"))t.print();
            }
            priority--;
        }
    }

}
