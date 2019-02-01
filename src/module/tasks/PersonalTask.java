package module.tasks;

import module.TaskLists.TaskList;

public class PersonalTask extends Task {


    public PersonalTask(String toDo, String time, String date, String status, TaskList tlist){
        super(toDo, time, date, status, tlist);
        type = "Personal";
        data.add(type);
        priority = 1;
    }

//    public PersonalTask(){
//        super();
//        if(taskName.equals("cancel"))return;
//        type = "Personal";
//        data.add(type);
//        priority = 1;
//    }



}
