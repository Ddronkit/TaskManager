package gui.tasklistDisplay;

import gui.AlertWindows.PopUpWindows;
import gui.tasklistWindow.TasklistWindow;
import gui.tasksWindow.TaskWindow2;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import module.TaskLists.TaskList;
import module.tasks.NormalTask;
import module.tasks.Task;
import ui.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskDisplay2{
    private  List<TaskList> list;
    private  Account account;
    private  Map<String,BorderPane> nboxs = new HashMap<>();
    private  Map<String,BorderPane> fboxs = new HashMap<>();
    private  BorderPane mainPane;
    private  Integer middleHeight = 500;
    private  ComboBox<String> listSelection;
    private  Integer listLimit = 10;



    public void display(Account ac, BorderPane pane){
        account = ac;
        list = account.getTaskList();
        mainPane = pane;
        loadTaskLists();
    }

    private void loadTaskLists(){
        BorderPane taskListFrame = new BorderPane();
//        taskListFrame.getStylesheets().add(account.getTheme().getDisplaytheme());

        taskListFrame.setPadding(new Insets(10,10,10,10));
        HBox topPane = makeTBPane();
        listSelection = selectionBox();

        for(TaskList tlist : list){
            GridPane ngrid = makeNewGrid();
            GridPane fgrid = makeNewGrid();

            BorderPane normalPane = new BorderPane();
//            setTaskPane(taskPane, checkPane, editPane, botPane, normalPane,ngrid);
            nboxs.put(tlist.name(), normalPane);

            BorderPane finishedPane = new BorderPane();
//            setTaskPane(makeMiddlePane(300), makeMiddlePane(55), makeMiddlePane(55), makeTBPane(), finishedPane,fgrid);
            fboxs.put(tlist.name(),finishedPane);


            listSelection.getItems().add(tlist.name()+completionDisplay(tlist));

            loadTask(tlist, normalPane, finishedPane,ngrid,fgrid);

//            loadToDoBotPane(tlist, botPane);


        }
        listSelection.setOnAction(event -> {
            String list = listSelection.getValue();
            taskListFrame.setCenter(nboxs.get(list.substring(0,list.length()-7).trim()));
        } );

        Button addListBut = new Button("New List");
        addListBut.setMinWidth(79);
        Button editListBut = new Button("Edit List");
        editListBut.setMinWidth(79);

        addListBut.setOnAction(event -> {
            String list = listSelection.getValue();
            new TasklistWindow().display(account.getTaskList(list.substring(0,list.length()-7).trim()),this, true);
        });
        editListBut.setOnAction(event -> {
            String list = listSelection.getValue();
            new TasklistWindow().display(account.getTaskList(list.substring(0,list.length()-7).trim()), this, false);
        });

        topPane.getChildren().addAll(listSelection, addListBut,editListBut);
        defaultTaskList(taskListFrame, topPane, listSelection);
        mainPane.setCenter(taskListFrame);
    }

    private void setTaskPane(VBox midBox, HBox botPane, BorderPane middlePane, GridPane taskGrid) {
        middlePane.setPadding(new Insets(10,0,0,0));
        middlePane.setCenter(midBox);

//        middlePane.setCenter(taskGrid);
        middlePane.setBottom(botPane);
    }

    private void defaultTaskList(BorderPane taskListFrame, HBox topPane, ComboBox<String> listSelection) {
        taskListFrame.setTop(topPane);
        taskListFrame.setCenter(nboxs.get(list.get(0).name()));
        listSelection.setValue(list.get(0).name()+completionDisplay(list.get(0)));
    }

    private ComboBox<String> selectionBox(){
        ComboBox<String> listSelection = new ComboBox<>();
        listSelection.setMinWidth(290);
        listSelection.setMinHeight(20);
        return listSelection;
    }

    private void loadTask(TaskList tlist, BorderPane normalPane, BorderPane finishedPane, GridPane ngrid, GridPane fgrid){
        VBox midBoxn = makeMiddlePane(410);
        HBox botPane = makeTBPane();
        VBox midBoxf = makeMiddlePane(410);
        HBox botPane2 = makeTBPane();


        for(Task task : tlist.getTasks()){
            if(!task.completed()){
                VBox taskPane = makeVBoxWidth(290);
                VBox checkPane = makeVBoxWidth(80);
                VBox editPane = makeVBoxWidth(80);
                HBox rowPane = new HBox(10);
                Label name = new Label(task.getTaskString());
//                name.setMinHeight(25);
                name.setMinWidth(290);
                name.setPadding(new Insets(0,0,0,5));
                Button checkButton = new Button("✓");
                checkButton.setMinHeight(10);
                Button editButton = new Button("edit");
                editButton.setMinHeight(10);

                taskPane.getChildren().add(name);
                taskPane.setAlignment(Pos.CENTER_LEFT);
                checkPane.getChildren().add(checkButton);
                editPane.getChildren().add(editButton);
                rowPane.getChildren().addAll(taskPane,checkPane,editPane);

                midBoxn.getChildren().add(rowPane);


                checkButton.setOnAction(event -> {

                    task.setCompleted();
                    update(tlist,false);
                });

                editButton.setOnAction(event -> {
                    new TaskWindow2().display(task,this,false);
                });
            }
            else{
                Label name = new Label(task.getTaskString());
                name.setPadding(new Insets(0,0,0,5));
                Button undoBut = new Button("undo");
                Button editButton = new Button("edit");

                VBox taskPane2 = makeVBoxWidth(290);
                VBox checkPane2 = makeVBoxWidth(80);
                VBox editPane2 = makeVBoxWidth(80);
                HBox rowPane = new HBox(10);
                taskPane2.setAlignment(Pos.CENTER_LEFT);

                taskPane2.getChildren().add(name);
                checkPane2.getChildren().add(undoBut);
                editPane2.getChildren().add(editButton);

                rowPane.getChildren().addAll(taskPane2,checkPane2,editPane2);
                midBoxf.getChildren().add(rowPane);

                editButton.setOnAction(event -> {
                    new TaskWindow2().display(task,this,false);
                });

                undoBut.setOnAction(event -> {
                    if(tlist.getNumOfTask()>=listLimit){
                        new PopUpWindows().quickPop("List is full, cannot undo.");
                    }
                    else{
                        task.setNotCompleted();
                        update(tlist,true);
                    }
                });
            }
        }
        loadToDoBotPane(tlist,botPane);
        loadFinishedBotPane(tlist,botPane2);

        setTaskPane(midBoxn,botPane,normalPane,ngrid);
        setTaskPane(midBoxf,botPane2,finishedPane,fgrid);

    }

    private void loadToDoBotPane(TaskList tlist, HBox botPane){
        Button addBut = new Button("+");
        addBut.setShape(new Circle(40));

        TextField quickadd = new TextField();
        quickadd.setPromptText("Quick add task");
        quickadd.setMinWidth(345);
        quickadd.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    if(tlist.getNumOfTask()< listLimit && !quickadd.getText().equals("")){
                        tlist.addTask(quickadd.getText(),"","","false");
                        update(tlist,false);
                    }
                    else{
                        new PopUpWindows().quickPop("Your list is full, can't add any new task.");
                        quickadd.clear();
                    }
                }
            }
        });

        Button completedBut = new Button("Completed");
        botPane.setAlignment(Pos.CENTER_RIGHT);

        completedBut.setOnAction(event -> goToList(tlist,true));
        addBut.setOnAction(event -> {
            if(tlist.getNumOfTask()< listLimit){
                if(quickadd.getText().equals("")){
                    Task task = new NormalTask("Unspecified task","","","false", tlist);
                    new TaskWindow2().display(task,this,true);
                }
                else {
                    tlist.addTask(quickadd.getText(),"","","false");
                    update(tlist,false);
                }
            }
            else{new PopUpWindows().quickPop("Your list is full, can't add any new task.");};
        });

        botPane.getChildren().addAll(quickadd,addBut,completedBut);
    }

    private void loadFinishedBotPane(TaskList tlist, HBox botPane){
        botPane.setAlignment(Pos.CENTER_RIGHT);
        Button goBackBut = new Button("Undone");

        Button clearBut = new Button("Clear All");
        clearBut.setOnAction(event -> {
            tlist.clearFinished();
            update(tlist,false);
        });

        goBackBut.setOnAction(event -> {
            goToList(tlist,false);
        });

        botPane.getChildren().addAll(clearBut,goBackBut);
    }

    private VBox makeMiddlePane(Integer width){
        VBox pane = new VBox(10);
        pane.setMinWidth(width);
        pane.setMinHeight(middleHeight);
        return pane;
    }

    private VBox makeVBoxWidth(Integer width){
        VBox pane = new VBox();
        pane.setMinWidth(width);
        return pane;
    }

    private HBox makeTBPane(){
        HBox pane = new HBox(10);
        pane.setMinHeight(20);
        pane.setMinWidth(410);
        return pane;
    }

    private GridPane makeNewGrid(){

        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setMinWidth(410);
        return grid;

    }

    public void update(TaskList tlist,Boolean sublist){
        loadTaskLists();
        goToList(tlist, sublist);
    }

    private String completionDisplay(TaskList tlist){
        int numtask = tlist.getTasks().size()-tlist.getNumOfTask();
        int tlsize = tlist.getTasks().size();
        return "     ("+Integer.toString(numtask) +"/"+Integer.toString(tlsize)+")";
    }

    private void goToList(TaskList tlist, Boolean sublist) {
        Node pane = mainPane.getCenter();
        BorderPane taskPane = (BorderPane) pane;
        if(!sublist) taskPane.setCenter(nboxs.get(tlist.name()));
        else taskPane.setCenter(fboxs.get(tlist.name()));
        listSelection.setValue(tlist.name()+completionDisplay(tlist));
    }

    public void refresh(){
        String list = listSelection.getValue();
        update(account.getTaskList(list.substring(0,list.length()-7).trim()),false);
    }


}
