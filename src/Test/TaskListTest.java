package Test;


import module.TaskLists.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Account;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList list1;

    @BeforeEach
    public void runBefore(){
        list1 = new TaskList("test", new Account("Test"));

    }


    @Test
    public void testAddTask(){
        list1.addTask("School","09:00", "09/24","false");
        list1.addTask("Date","10:00", "","");
        list1.addTask("Lunch","", "09/00","");
        list1.addTask("Homework","", "","");

        assertEquals(list1.getTasks2().get(0).getData(), new ArrayList<>(Arrays.asList("School","09:00", "09/24","false","Normal")));
        assertEquals(list1.getTasks2().get(1).getData(), new ArrayList<>(Arrays.asList("Date","10:00", "","true","Normal")));
        assertEquals(list1.getTasks2().get(2).getData(), new ArrayList<>(Arrays.asList("Lunch","", "09/00","true","Normal")));
        assertEquals(list1.getTasks2().get(3).getData(), new ArrayList<>(Arrays.asList("Homework","", "","true","Normal")));
    }

    @Test
    public void testFinishedTask(){
        list1.addTask("School","09:00", "09/24","");
        list1.addTask("Date","10:00", "09/24","");
        list1.addTask("Lunch","12:00", "09/00","");
        list1.addTask("Homework","09:00", "09/04","");

        list1.finishedTask("School");
        list1.finishedTask("Lunch");

        assertEquals(list1.getTasks2().get(0).getData(), new ArrayList<>(Arrays.asList("School","09:00", "09/24","false","Normal")));
        assertEquals(list1.getTasks2().get(1).getData(), new ArrayList<>(Arrays.asList("Date","10:00", "09/24","true","Normal")));
        assertEquals(list1.getTasks2().get(2).getData(), new ArrayList<>(Arrays.asList("Lunch","12:00", "09/00","false","Normal")));
        assertEquals(list1.getTasks2().get(3).getData(), new ArrayList<>(Arrays.asList("Homework","09:00", "09/04","true","Normal")));

    }
}
