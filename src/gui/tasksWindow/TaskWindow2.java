package gui.tasksWindow;

import gui.AlertWindows.PopUpWindows;
import gui.tasklistDisplay.TaskDisplay2;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import module.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskWindow2 {

    public void display(Task task, TaskDisplay2 taskDisplay, Boolean newTask){
        Stage window = new Stage();
        HBox layout0 = new HBox(85);
        HBox layout1 = new HBox(10);
        HBox layout3 = new HBox(10);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        TextField taskName = new TextField();
        TextField taskTime = new TextField();
        TextField taskDate = new TextField();

        Label nameLabel = new Label("Task");
        Label timeLabel = new Label("Time");
        Label dateLabel = new Label("Date");


        String name,time,date;
        if(newTask){
            name="";time="";date="";
            Button addButton = new Button("Add");
            addButton.setOnAction(event -> {
                if(isName(taskName.getText()) && setTime(taskTime.getText(),task) && setDate(taskDate.getText(),task)){
                    task.getList().addTask(taskName.getText(),taskTime.getText(),taskDate.getText(),"false");
                    taskDisplay.update(task.getList(),false);
                    window.close();
                }
                else {new PopUpWindows().quickPop("Error in entered data");}
            });
            Button cancelBut = new Button("Cancel");
            cancelBut.setOnAction(event -> window.close());
            layout3.getChildren().addAll(addButton,cancelBut);
        }
        else{
            name = task.getTaskName();time=task.getTaskTime();date=task.getDateString();
            Button change = new Button("Change");
            change.setOnAction(event -> {
                List<String> data  = new ArrayList<>();
                data.add(taskName.getText().trim());
                data.add(taskTime.getText().trim());
                data.add(taskDate.getText().trim());
                if(!data.equals(task.getData().subList(0,2))){
                    if(isName(taskName.getText()) && setTime(taskTime.getText(),task) && setDate(taskDate.getText(),task)){
                        task.changeTask(data.get(0),data.get(1),data.get(2),task.completed().toString());
                        taskDisplay.update(task.getList(),task.completed());
                        window.close();
                    }
                    else {new PopUpWindows().quickPop("Error in entered data");}
                }

            });
            layout3.getChildren().addAll(change);
        }
        taskName.setText(name);taskTime.setText(time);taskDate.setText(date);



        taskName.setPromptText("Task");
        taskTime.setPromptText("00:00-23:59");
        taskDate.setPromptText("MM/DD");

        layout0.getChildren().addAll(nameLabel,timeLabel,dateLabel);

        layout1.getChildren().addAll(taskName,taskTime,taskDate);
        layout1.setAlignment(Pos.CENTER);

        layout3.setAlignment(Pos.BASELINE_RIGHT);



        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(layout0, layout1, layout3);


        Scene scene = new Scene(layout,350, 150);
        scene.getStylesheets().add(task.getList().getAccount().getTheme().getDisplaytheme());
        window.setScene(scene);
        window.showAndWait();
    }

    public void displayNew(){

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
