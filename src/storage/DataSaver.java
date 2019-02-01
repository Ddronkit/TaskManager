package storage;

import module.TaskLists.ShoppingList;
import module.TaskLists.TaskList;
import module.tasks.Task;
import ui.Account;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DataSaver {

    public static void saveData(Account account) throws IOException {
        List<TaskList> list = account.getTaskList();
        String FileName = account.getAccountName() + ".txt";
        PrintWriter pw = new PrintWriter(new FileOutputStream(FileName));
        for (TaskList tl : list) {
            if(tl instanceof ShoppingList)pw.println(tl.name().trim()+" @");
            else pw.println(tl.name().trim());
//            for (String key : tl.getTasks2().keySet()) {
//                Task t = tl.getTasks2().get(key);
//                pw.println(t.getData());
//            }
            for (Task task: tl.getTasks()){
                pw.println(task.getData());
            }
            pw.println();
        }
        pw.println(account.getTheme() + "**");
        pw.close();
    }

}
