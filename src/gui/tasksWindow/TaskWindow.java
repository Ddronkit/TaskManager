package gui.tasksWindow;

import gui.tasklistDisplay.TaskDisplay;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import module.TaskLists.TaskList;
import module.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskWindow {


    public static void display(Task task, TaskDisplay taskDisplay, GridPane grid, List<GridPane> grids){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        TextField taskName = new TextField(task.getTaskName());
        TextField taskTime = new TextField(task.getTaskTime());
        TextField taskDate = new TextField(task.getDateString());

        Label name = new Label("Task");
        Label time = new Label("Time");
        Label date = new Label("Date");

        Button change = new Button("Change");
        change.setOnAction(event -> {
            List<String> data  = new ArrayList<>();
            data.add(taskName.getText().trim());
            data.add(taskTime.getText().trim());
            data.add(taskDate.getText().trim());
            if(!data.equals(task.getData().subList(0,2)))task.changeTask(data.get(0),data.get(1),data.get(2),task.completed().toString());
            taskDisplay.update(grid,grids.indexOf(grid),false);
            window.close();
        });



        HBox layout0 = new HBox(65);
        layout0.getChildren().addAll(name,time,date);

        HBox layout1 = new HBox(10);
        layout1.getChildren().addAll(taskName,taskTime,taskDate);
        layout1.setAlignment(Pos.CENTER);

        HBox layout3 = new HBox();
        layout3.getChildren().add(change);
        layout3.setAlignment(Pos.BASELINE_RIGHT);



        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(layout0, layout1, layout3);


        Scene scene = new Scene(layout,300, 250);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void displaynew(Task task, TaskList tlist, TaskDisplay taskDisplay, GridPane grid, List<GridPane> grids){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        TextField taskName = new TextField();
        TextField taskTime = new TextField();
        TextField taskDate = new TextField();

        taskName.setPromptText("Task");
        taskTime.setPromptText("00:00-23:59");
        taskDate.setPromptText("MM/DD");

        Label name = new Label("Task");
        Label time = new Label("Time");
        Label date = new Label("Date");

        Label ermsg = new Label();

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            if(isName(taskName.getText()) && setTime(taskTime.getText(),task) && setDate(taskDate.getText(),task)){
                task.getList().addTask(taskName.getText(),taskTime.getText(),taskDate.getText(),"false");
                taskDisplay.update(grid,grids.indexOf(grid),false);
                window.close();
            }
            else ermsg.setVisible(true);
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            window.close();
        });



        HBox layout0 = new HBox(65);
        layout0.getChildren().addAll(name,time,date);

        HBox layout1 = new HBox(10);
        layout1.getChildren().addAll(taskName,taskTime,taskDate);
        layout1.setAlignment(Pos.CENTER);

        HBox layout3 = new HBox(10);
        layout3.getChildren().addAll(addButton,cancelButton);
        layout3.setAlignment(Pos.BASELINE_RIGHT);



        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(layout0, layout1, layout3,ermsg);


        Scene scene = new Scene(layout,300, 250);
        window.setScene(scene);
        window.show();

    }

    private static boolean isName(String message){
        if(message.equals(""))return false;
        else return true;
    }

    private static boolean setTime(String message,Task t){
        if(message.equals(""))return true;
        try {
            t.setTime(message);
            return true;
        }
        catch(Exception e){
            System.out.println("Error writing time");
            return false;
        }
    }

    private static boolean setDate(String message, Task t){
        if(message.equals(""))return true;
        try {
            t.setDate(message);
            return true;
        }
        catch(Exception e){
            System.out.println("Error writing date");
            return false;
        }
    }
}
