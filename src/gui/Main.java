package gui;

import gui.menuDisplay.MainMenu;
import gui.tasklistDisplay.TaskDisplay;
import gui.tasklistDisplay.TaskDisplay2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.Account;

import java.util.Observable;
import java.util.Observer;

public class Main extends Application implements Observer{
    private Account account = new Account("User1");

    Stage window;
    BorderPane mainPane= new BorderPane();
    Scene scene;
    TaskDisplay tdisplay = new TaskDisplay();
    TaskDisplay2 tdisplay2 = new TaskDisplay2();
    MainMenu menu = new MainMenu();
    Integer size = 500;
    Double ratio = 1.3;
    String currentTheme;
    Boolean saveStatus = false;




    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("TaskMask");

        displayTask();

        menu.display(mainPane, account);
        menu.addObserver(this);

        scene = new Scene(mainPane, size, size*ratio);
        currentTheme = account.getTheme().getDisplaytheme();
        scene.getStylesheets().add(currentTheme);

        window.setScene(scene);
        window.setOnCloseRequest(event -> {
            if(saveStatus==false){
                if(ConfirmBox.display("Close Request","Do you want to save?")){
                    account.savetheLists();
                }
            }

        });
        window.show();


    }

    private void displayTask(){
        account.loadtheList();
        tdisplay2.display(account, mainPane);
//        tdisplay.display(account.getTaskList(),mainPane);
    }



    @Override
    public void update(Observable o, Object arg) {
        refreshTheme();
        tdisplay2.refresh();
        saveStatus = false;
    }

    private void refreshTheme() {
        scene.getStylesheets().remove(currentTheme);
        currentTheme = account.getTheme().getDisplaytheme();
        scene.getStylesheets().add(currentTheme);
    }
}
