package module.TaskLists;

import exceptions.NullItemNameException;
import exceptions.TooMuchTaskException;
import module.tasks.NormalTask;
import module.tasks.PersonalTask;
import module.tasks.Task;
import module.tasks.UrgentTask;
import tools.Printer;
import tools.PrioritySorter;
import tools.UserInput;
import ui.Account;

import java.util.*;


public class TaskList {
    protected Map<String, Task> tasks = new HashMap<>();
    protected List<Task> tasks2 = new ArrayList<>();
    protected Account account;
    protected String listName;
    protected String keyInput;
    protected Scanner scanner = new Scanner(System.in);
    protected int listLimit = 10;
    protected Printer printer = new Printer();
    protected UserInput input = new UserInput();
    protected boolean completed = true;
    protected PrioritySorter sorter = new PrioritySorter();


    //MODIFIES: this
    //EFFECTS: Construct a new TaskList with the input being the listName
    public TaskList(String listName, Account ac){
        this.listName = listName;
        account = ac;
    }


    //EFFECTS: print out the list and ask user for new task to be added if required, then reprint the list.
    public void run()throws TooMuchTaskException{
        printThisList();
//        operationList();
    }



    //MODIFIES: this
    //EFFECTS: add new task with user input: task time and date. Then add to the current list. If list reached the listLimit, then throw TooMuchTaskException.
//    protected void addTask(String type)throws TooMuchTaskException{
//        if(getNumOfTask()==listLimit)throw new TooMuchTaskException();
//        String ToDo;
//        try {
//            ToDo = inputToDo();
//        } catch (NullItemNameException ntn) {
//            printer.nullItemName();
//            return ;
//        }
//        if(ToDo.equals("cancel") || ToDo.equals("quit"));
//        else{
//            if(type.equals("N")) {tasks.put(ToDo, new NormalTask(ToDo,inputTime(), inputDate(), "", this));}
//            if(type.equals("P")) {tasks.put(ToDo, new PersonalTask(ToDo, inputTime(), inputDate(),"", this));}
//            if(type.equals("U")) {tasks.put(ToDo, new UrgentTask(ToDo, inputTime(), inputDate(),"",this));}
//        }
//    }


    //MODIFIES: status of the task with specified taskName
    //EFFECTS: change the status of the specified task to false(hence done), if task doesn't exist return message.
    public void finishedTask(String taskName){
        if(tasks.get(taskName) != null){
            if(!tasks.get(keyInput).completed())tasks.get(keyInput).setCompleted();
            else printer.taskAlreadyFinished();
        }
        else printer.itemNotExist(this);
    }

//    //MODIFIES: status of the task with specified taskName
//    //EFFECTS: change the status of the specified task to false(hence done), if task doesn't exist return message.
//    public void finishedTask(){
//        System.out.println("What is it?");
//        keyInput = scanner.nextLine();
//        finishedTask(keyInput);
//    }


    //EFFECTS: print out the list. if list have nothing print message.
    public void printThisList(){
        printer.printTasks(this);
    }

//    public void sortPrint(String type){
//        int priority = 2;
//        while(priority>=0){
//            for(String key: tasks.keySet()){
//                Task t = tasks.get(key);
//                if(t.getPriority()==priority && t.completed()&& type.equals("unfinished"))t.print();
//                if(t.getPriority()==priority && !t.completed()&& type.equals("finished"))t.print();
//            }
//            priority--;
//        }
//    }

    //EFFECTS: print out the finished module.tasks. If nothing then print message.
    protected void printFinished(){
        printer.printFinished(this);
    }

//    public int getNumOfTask(){
//        int numberOfTask = 0;
//        for(String key: tasks.keySet()){
//            Task t = tasks.get(key);
//            if(!t.completed())numberOfTask++;
//        }
//        return numberOfTask;
//    }

    public void clearFinished(){
        for(int i =0 ; i< tasks2.size(); i++){
            if(tasks2.get(i).completed()){
                tasks2.remove(i);
                i--;
            }
        }
    }

    public int getNumOfTask(){
        int numberOfTask=0;
        for(Task task: tasks2){
            if(!task.completed())numberOfTask++;
        }
        return numberOfTask;
    }

    public void changeName(String newName){
        listName = newName;
    }

//    //EFFECTS: list of operations available for the user, if user input matches options, run operation. If not print error message.
//    protected void operationList()throws TooMuchTaskException{
//        while(true) {
//            printer.functionHeading(this);
//            keyInput = scanner.nextLine();
//            if (keyInput.equals("at") || keyInput.equals("add task")) addTask("N");
//            else if (keyInput.equals("aut") || keyInput.equals("add urgent task")) addTask("U");
//            else if (keyInput.equals("apt") || keyInput.equals("add personal task")) addTask("P");
//            else if (keyInput.equals("pt") || keyInput.equals("print module.tasks")) printThisList();
//            else if (keyInput.equals("ft") || keyInput.equals("finish task")) finishedTask();
//            else if (keyInput.equals("pft") || keyInput.equals("print finished module.tasks"))printFinished();
//            else if (keyInput.equals("quit") || keyInput.equals("q")) break;
//            else printer.funcUnavailable();
//        }
//    }




    //EFFECTS: get the user input for task to do in new task.
    protected String inputToDo()throws NullItemNameException {
        return input.getItemName(this);
    }
//
//    //EFFECTS: get the user input for time in new task.
//    protected String inputTime(){
//        return input.getTaskTime();
//    }
//
//    //EFFECTS: get the user input for date in new task.
//    protected String inputDate(){
//        return input.getTaskDate();
//    }

    //EFFECTS: Return the name of the list
    public String name(){
        return listName;
    }

    public Account getAccount() {
        return account;
    }

    //EFFECTS: Return the module.tasks in the Task List
    public Map<String,Task> getTasks2(){
        return tasks;
    }

    public void sortTasksA(){
        tasks2.sort(Comparator.comparing(Task::getTaskName));
    }

    public void sortTasksD(){
        sorter.sortTasks(tasks2);
    }


    public List<Task> getTasks(){
        return tasks2;
    }

    public List<List<String>> getData(){
        List<List<String>> data = new ArrayList<>();
        for(String key: tasks.keySet()){
            data.add(tasks.get(key).getData());
        }

        return data;
    }


    //MODIFIES: this
    //EFFECTS: add new task specified task time and date. Then add to the current list.
    public void addTask(String toDo, String time, String date, String status){
        tasks2.add(new NormalTask(toDo, time, date,status, this));
        tasks.put(toDo, new NormalTask(toDo, time, date,status, this));
    }

    public void addPersonalTask(String toDo, String time, String date, String status){
        tasks2.add(new PersonalTask(toDo, time, date,status, this));
        tasks.put(toDo, new PersonalTask(toDo, time, date,status, this));
    }
    public void addUrgentTask(String toDo, String time, String date, String status){
        tasks2.add(new UrgentTask(toDo, time, date,status,this));
        tasks.put(toDo, new UrgentTask(toDo, time, date,status, this));
    }


}
