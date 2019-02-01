package module.tasks;

import module.TaskLists.TaskList;
import tools.Printer;

import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Task {
    protected TaskList list;
    protected List<String> data;
    protected String taskName;
    protected String taskTime;
//    protected String taskDate;
    protected Calendar taskDate;
    protected Boolean completed =false;
    protected String type = "";
    protected int priority = 0;
    protected Printer printer = new Printer();


    protected Task(String toDo, String time, String date, String status, TaskList taskList){
        if(status.equals("true")) {
            this.completed = true;
        }
        data = new ArrayList<>(Arrays.asList(toDo,time,date,Boolean.toString(completed)));
        taskName = toDo;
        taskTime = time;
        if(!date.equals(""))setDate(date);
        if(!time.equals(""))setTime(time);
        list = taskList;
    }



    //MODIFIES: this.status
    //EFFECTS: change the status(boolean) from one to another true to false ; false to true, as well as listed in the task data array.
    public void setCompleted(){
        completed = true;
        data.set(3, Boolean.toString(completed));
    }

    public void setNotCompleted(){
        completed = false;
        data.set(3,Boolean.toString(completed));
    }


    //EFFECTS: return the data array
    public List<String> getData(){
        return data;
    }

    //EFFECTS: print the task with specific type and data.
    public void print() {
        printer.taskPrint(this);
    }


    public void  changeTask(String toDo, String time, String date, String status){
        taskName = toDo;
        taskTime = time;
        if(!date.equals(""))setDate(date);
        else taskDate = null;
        if(!time.equals(""))setTime(time);
        data.set(0,toDo);
        data.set(1,time);
        data.set(2,date);
        if(status.equals("true"))setCompleted();

    }

    public void setTime(String time){
//        try {
            String[] data = time.split(":");
            if(taskDate == null)taskDate = new GregorianCalendar();
            taskDate.set(Calendar.HOUR_OF_DAY, Integer.valueOf(data[0]));
            taskDate.set(Calendar.MINUTE, Integer.valueOf(data[1]));
//        }
//        catch (Exception e){
//            System.out.println("Error in loading date.");
//        }
    }

    public void setDate(String date){
//        try {
            String[] data = date.split("/");
            taskDate = new GregorianCalendar();
            taskDate.set(Calendar.MONTH, Integer.valueOf(data[0])-1);
            taskDate.set(Calendar.DAY_OF_MONTH, Integer.valueOf(data[1]));
//        }
//        catch (Exception e){
//            System.out.println("Error in loading date.");
//        }



    }

    public String getTaskString(){
        if(getTaskTime().equals("")){
            if(getDateString().equals(""))return getTaskName();
            else return getTaskName() + " on " + getDateString();}
        else if (getDateString().equals(""))return getTaskName() + " at " + getTaskTime();
        else return getTaskName() + " at " + getTaskTime() + " on " + getDateString();
    }

    public String getTaskName(){
        return taskName;
    }

    public String getTaskTime(){
        if(!taskTime.equals("")){
            SimpleDateFormat sdf = new SimpleDateFormat("hh':'mm");
            return sdf.format(taskDate.getTime());
        }
        else return taskTime;
    }

//    public String getDateString(){
//        return taskDate;
//    }

    public Date getDate(){
        if(taskDate==null)return null;
        else return taskDate.getTime();
    }

    public TaskList getList() {
        return list;
    }

    public String getDateString(){
        if(taskDate !=null){
//            String month = Integer.toString(taskDate.get(Calendar.MONTH));
//            String day = Integer.toString(taskDate.get(Calendar.DAY_OF_MONTH));
//            return month+"/"+
//            SimpleDateFormat sdf = new SimpleDateFormat("d 'of' MMMMMM");
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
            return sdf.format(taskDate.getTime());
        }
        else return "";
    }

    public Boolean completed(){
        return completed;
    }

    public int getPriority(){return priority;}

    public String getType(){
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(list, task.list) &&
                Objects.equals(taskName, task.taskName) &&
                Objects.equals(taskTime, task.taskTime) &&
                Objects.equals(taskDate, task.taskDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(list, taskName, taskTime, taskDate);
    }

}
