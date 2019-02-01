package gui.tasklistDisplay;

import gui.tasksWindow.TaskWindow;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import module.TaskLists.TaskList;
import module.tasks.NormalTask;
import module.tasks.Task;
import webStuff.ReadWebPageEx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class TaskDisplay extends Observable{
    private  List<TaskList> list;
    private  List<GridPane> grids = new ArrayList<>();
    private  BorderPane pane;
    private String style = "-fx-background-color: rgba(204, 255, 255);";


    public  void display(List<TaskList> taskLists, BorderPane pane){
        list = taskLists;


        this.pane = pane;

        loadTaskList(list,this.pane,0);

    }

    private void loadTaskList(List<TaskList> list, BorderPane pane, Integer position){
        for(TaskList t : list){
            loadOneList(t,pane,null,position,false, false);
        }

    }
    private void loadOneList(TaskList tlist,BorderPane pane,GridPane grid, Integer position, Boolean refresh, Boolean sublist){
        GridPane ngrid = makeNewGrid();
        ngrid.setMinWidth(400);
        GridPane fgrid = makeNewGrid();
        fgrid.setMinWidth(400);
        ngrid.setAlignment(Pos.TOP_CENTER);
        fgrid.setAlignment(Pos.TOP_CENTER);
        ngrid.setStyle(style);
        fgrid.setStyle(style);


        Button lbut = new Button("<");
        lbut.setMinWidth(55);
        GridPane.setConstraints(lbut, 0, 0);
        Button rbut = new Button(">");
        rbut.setMinWidth(55);
        GridPane.setConstraints(rbut,2,0);
        Button addBut = new Button("+");
        addBut.setMinWidth(55);
        GridPane.setConstraints(addBut,2,12);
        addBut.setStyle("-fx-background-color: rgba(0, 252, 127);");


        Button goCompletedBut = new Button("Completed");
        GridPane.setConstraints(goCompletedBut, 2,13);
        goCompletedBut.setOnAction(event -> pane.setCenter(fgrid));

        Label name = new Label(tlist.name());
        name.setMinWidth(300);
        GridPane.setConstraints(name, 1, 0);
        Label addError = new Label("You have reached the maximum task in this list");
        GridPane.setConstraints(addError, 1, 12);
        addError.setVisible(false);

        PauseTransition visiblePause = new PauseTransition(Duration.seconds(1.5));
        visiblePause.setOnFinished(event -> addError.setVisible(false));
        addBut.setOnAction(event -> {
            if(tlist.getNumOfTask()>=10){
                addError.setVisible(true);
                visiblePause.play();
            }
            else addTask(tlist,ngrid);
        });




        ngrid.getChildren().addAll(lbut, name, rbut,addBut,addError,goCompletedBut);

        finishedGrid(fgrid,ngrid,pane,tlist);

        if(refresh){
            loadTasks(ngrid, fgrid, list.get(position),false);
            grids.set(grids.indexOf(grid),ngrid);
            if(!sublist){
                pane.setCenter(ngrid);
            }
            else{
                pane.setCenter(fgrid);
            }
        }
        else{
            loadTasks(ngrid, fgrid,tlist, false);
            grids.add(ngrid);
            if(list.indexOf(tlist)==position && !sublist){pane.setCenter(ngrid);}
        }




//        if(list.indexOf(tlist)==position && !sublist){pane.setCenter(ngrid);}
//        if(list.indexOf(tlist)==position && sublist){pane.setCenter(gridList.get(1));}
        if(list.indexOf(tlist)==0&&list.size()>1){
            lbut.setOnAction(event -> {pane.setCenter(grids.get(list.size()-1));});
            rbut.setOnAction(event -> {pane.setCenter(grids.get(1)); });
        }
        else if(list.indexOf(tlist)<list.size()-1 && list.indexOf(tlist)!=0){
            lbut.setOnAction(event -> {pane.setCenter(grids.get(list.indexOf(tlist)-1));});
            rbut.setOnAction(event -> {pane.setCenter(grids.get(list.indexOf(tlist)+1));});

        }
        else if(list.indexOf(tlist)==list.size()-1){
            lbut.setOnAction(event -> {pane.setCenter(grids.get(list.indexOf(tlist)-1)); });
            rbut.setOnAction(event -> {pane.setCenter(grids.get(0)); });

        }
    }

    private void finishedGrid(GridPane fgrid,GridPane ngrid,BorderPane pane, TaskList tlist){

        Label name = new Label(tlist.name()+": Completed");
        name.setMinHeight(25);
        name.setMinWidth(300);
        GridPane.setConstraints(name, 1, 0);
        loadTasks(ngrid, fgrid, tlist,true);

        Button goBackButton = new Button("To Do");
        GridPane.setConstraints(goBackButton,2,12);
        goBackButton.setOnAction(event -> pane.setCenter(ngrid));

        fgrid.getChildren().addAll(name,goBackButton);


    }

    private void loadTasks(GridPane ngrid,GridPane fgrid, TaskList list, Boolean completed){
        int row = 1;

        for(String key : list.getTasks2().keySet()){
            Task task = list.getTasks2().get(key);
            if(!completed){
                if(!task.completed()){
                    Button button = new Button("Done");
                    button.setMinWidth(55);
                    GridPane.setConstraints(button, 0, row);
                    Label label = new Label(task.getTaskString());
                    GridPane.setConstraints(label, 1, row);
                    Button editButton = new Button("Edit");

                    editButton.setMinWidth(55);
                    GridPane.setConstraints(editButton, 2, row);
                    editButton.setOnAction(event -> new TaskWindow().display(task,this,ngrid,grids));

                    ngrid.getChildren().addAll(button,label,editButton);
                    button.setOnAction(event -> {
                        ngrid.getChildren().remove(button);
                        ngrid.getChildren().remove(label);
                        ngrid.getChildren().remove(editButton);
                        task.setCompleted();
                        update(ngrid,grids.indexOf(ngrid),false);
                    });
                    row++;
                }
            }
            else{
                if(task.completed()){
                    Button button = new Button("Undo");
                    GridPane.setConstraints(button, 0, row);
                    Label label = new Label(task.getTaskString());
                    GridPane.setConstraints(label, 1, row);
                    Label errorUndo = new Label("List is full, can't undo");
                    GridPane.setConstraints(errorUndo, 1,12);
                    errorUndo.setMinWidth(325);
                    errorUndo.setVisible(false);

                    PauseTransition visiblePause = new PauseTransition(Duration.seconds(1));
                    visiblePause.setOnFinished(event -> errorUndo.setVisible(false));

                    fgrid.getChildren().addAll(button,label,errorUndo);
                    button.setOnAction(event -> {
                        if(list.getNumOfTask()<10){
                            fgrid.getChildren().remove(button);
                            fgrid.getChildren().remove(label);
                            task.setNotCompleted();
                            update(ngrid,grids.indexOf(ngrid),true);
                        }
                        else{
                            errorUndo.setVisible(true);
                            visiblePause.play();
                        }
                    });
                    row++;

                }
            }

        }
    }


    private  void addTask(TaskList tlist, GridPane grid){
        if(tlist.getNumOfTask()<10){
            Task task = new NormalTask("Unspecified task","","","false", tlist);
            new TaskWindow().displaynew(task,tlist,this,grid,grids);
        }

    }



    private GridPane makeNewGrid(){
//        ColumnConstraints col1 = new ColumnConstraints();
//        col1.setMinWidth(10);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
//        grid.getColumnConstraints().set(0, col1);
        return grid;

    }

    public void update(GridPane grid, Integer position, Boolean sublist){
        loadOneList(list.get(position), pane, grid, position, true, sublist);

    }

    public void printStuff(){
        System.out.println("I printed from observer");
        try {
            new ReadWebPageEx().display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
