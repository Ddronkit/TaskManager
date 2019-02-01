package webStuff;

import gui.tasklistDisplay.TaskDisplay;

import java.util.Observable;
import java.util.Observer;

public class Log implements Observer{
    private TaskDisplay td;

    @Override
    public void update(Observable o, Object arg) {
        td = (TaskDisplay) o;
        td.printStuff();
    }
}
