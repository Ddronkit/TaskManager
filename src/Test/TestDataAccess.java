package Test;

import module.TaskLists.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.DataLoader;
import storage.DataSaver;
import ui.Account;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataAccess {
    private DataSaver ds = new DataSaver();
    private DataLoader dl = new DataLoader();
    private ArrayList<TaskList> listList1 = new ArrayList<>();
    private ArrayList<TaskList> listList2 = new ArrayList<>();
    private Account ac = new Account("Test");
    private TaskList list1 = new TaskList("test1", ac);

    @BeforeEach
    public void runBefore(){
        list1.addTask("School","09:00", "09/24","false");
        list1.addTask("Date","10:00", "","");
        list1.addTask("Lunch","", "09/00","");
        list1.addTask("Homework","", "","");
    }

    @Test
    public void testRLSmall(){
        listList1.add(list1);
//        saveLists(listList1);
        loadList(listList2);
        assertEquals(listList1.get(0).getData(), listList2.get(0).getData());

    }

    private void loadList(ArrayList<TaskList> listList){
        try {
            dl.loadData(listList,ac);
        }
        catch (IOException i){
            System.out.println("Error loading data");
        }
    }

//    private void saveLists(ArrayList<TaskList> listList){
//        try {
//            ds.saveData(listList,"Test");
//        }
//        catch (IOException i){
//            System.out.println("Error in saving data");
//        }
//
//    }
}
