package module.tasks;

import module.TaskLists.TaskList;

public class NormalTask extends Task {
    protected String type = "Normal";

    public NormalTask(String toDo, String time, String date, String status, TaskList taskList){
        super(toDo, time, date, status, taskList);
        type = "Normal";
        data.add(type);

    }

//    public  NormalTask(){
//            super();
//            if(taskName.equals("cancel"))return;
//            type = "Normal";
//            data.add(type);
//    }

//    @Override
//    public void print() {
//        if(taskTime.equals(""))
//            if(taskDate.equals(""))System.out.printf(format,"",taskName);
//            else System.out.printf(format,"", taskName + " on " + taskDate);
//        else if (taskDate.equals(""))System.out.printf(format,"", taskName + " at " + taskTime);
//        else System.out.printf(format,"", taskName + " at " + taskTime + " on " + taskDate);
//    }

}
