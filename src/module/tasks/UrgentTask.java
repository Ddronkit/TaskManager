package module.tasks;

import module.TaskLists.TaskList;

public class UrgentTask extends Task {

    public UrgentTask(String toDo, String time, String date, String status, TaskList tlist) {
        super(toDo, time, date, status, tlist);
        type = "URGENT";
        data.add(type);
        priority = 2;
    }

}
