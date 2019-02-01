package gui.tasklistWindow;

import gui.tasklistDisplay.TaskDisplay2;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import module.TaskLists.TaskList;
import gui.AlertWindows.PopUpWindows;

public class TasklistWindow {

    public void display(TaskList taskList, TaskDisplay2 taskDisplay, Boolean newList){
        Stage window = new Stage();
        HBox topPanel = new HBox(10);
        HBox mainPanel = new HBox(10);
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,10,10,10));

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(350);

        Label nameLabel = new Label("Name");
        TextField listName = new TextField();
        listName.setMinWidth(100);
        listName.setPromptText("List Name");

        topPanel.getChildren().addAll(nameLabel);
        layout.getChildren().addAll(topPanel, mainPanel);

        if(newList){
            Button addBut = new Button("+");
            Button cancelBut = new Button("Cancel");

            cancelBut.setOnAction(event -> window.close());
            addBut.setOnAction(event -> {
                String name = listName.getText();
                if(isName(name)){
                    taskList.getAccount().addTaskList(name);
                    taskDisplay.update(taskList.getAccount().getTaskList(name),false);
                    window.close();
                }
                else new PopUpWindows().quickPop("Empty list name");
            });
            mainPanel.getChildren().addAll(listName,addBut,cancelBut);
        }
        else{
            Button changeBut = new Button("Change");
            Button deleteBut = new Button("Delete");

            listName.setText(taskList.name());

            deleteBut.setOnAction(event -> {
                if(new PopUpWindows().confrimBox("Confirm deletion")){
                    taskList.getAccount().removeTaskList(taskList);
                    taskDisplay.update(taskList.getAccount().getTaskList().get(0),false);
                    window.close();
                }
            });
            changeBut.setOnAction(event -> {
                String name = listName.getText();
                if(isName(name)){
                    taskList.changeName(name);
                    taskDisplay.update(taskList,false);
                    window.close();
                }
                else new PopUpWindows().quickPop("Empty list name");
            });
            mainPanel.getChildren().addAll(listName,changeBut,deleteBut);
        }

        Scene scene = new Scene(layout,300,150);
        scene.getStylesheets().add(taskList.getAccount().getTheme().getDisplaytheme());
        window.setScene(scene);
        window.showAndWait();




    }


    private static boolean isName(String message){
        if(message.equals(""))return false;
        else return true;
    }
}
