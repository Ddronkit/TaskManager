package tools;

import module.tasks.Task;

import java.util.List;


public class PrioritySorter {

    private int count;
    private int runtime;


    //EFFECTS: sort the module.tasks in task with the the priority number
    public void sortTasks(List<Task> tlist){
        runtime = 0;
        while (runtime <= tlist.size()){
            count = 0;
            for(Task task: tlist){
                if(tlist.indexOf(task) == tlist.size()-1 || tlist.size() == 1) {
                     break;
                }
                else {
                     Task nextTask = tlist.get(tlist.indexOf(task) + 1);

                     if(nextTask.getDate()==null);
                     else if (task.getDate()==null || task.getDate().after(nextTask.getDate())) {
                        count++;
                        tlist.set(tlist.indexOf(task), nextTask);
                        tlist.set(tlist.indexOf(nextTask)+1, task);
                    }

                }
            }
            runtime ++;
        }

    }



}
