package gui.menuDisplay;

import gui.theme.Themes;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import module.TaskLists.TaskList;
import ui.Account;

import java.util.Observable;


public class MainMenu extends Observable{

    private BorderPane mainPane;
    private Account account;
    private ColorSettings colorSettings = new ColorSettings();

    public void display(BorderPane pane, Account ac){
        mainPane=pane;
        account= ac;
        menuSelection();

    }

    private void menuSelection(){

        MenuBar menuBar = new MenuBar();

        Menu themeMenu = new Menu("Theme");
        Menu sortMenu = new Menu("Sort");


        makeThemeSeleciton(themeMenu);
        makeSortSelection(sortMenu);

        menuBar.getMenus().addAll(themeMenu,sortMenu);

        mainPane.setTop(menuBar);

//        HBox menuBar = new HBox(10);
//        menuBar.setStyle("-fx-background-color: rgba(255,255,255)");
//        ComboBox<String> themeSelection = new ComboBox<>();
//        themeSelection.setValue("Theme");
//
//        makeThemeSeleciton(themeSelection);
//
//        menuBar.getChildren().addAll(themeSelection);




    }

    private void makeThemeSeleciton(Menu menu){
        for(Themes theme: Themes.values()){
            MenuItem choice = new MenuItem(theme.toString());
            menu.getItems().add(choice);
            choice.setOnAction(event -> {
                account.changeTheme(theme);
                setChanged();
                notifyObservers();
            });
        }

    }

    private void makeSortSelection(Menu menu){
        MenuItem name = new MenuItem("Name");
        MenuItem date = new MenuItem("Date");
        menu.getItems().addAll(name,date);

        name.setOnAction(event -> {
            for (TaskList tlist : account.getTaskList()){
                tlist.sortTasksA();
                setChanged();
                notifyObservers();
            }
        });

        date.setOnAction(event -> {
            for (TaskList tlist : account.getTaskList()){
                tlist.sortTasksD();
                setChanged();
                notifyObservers();
            }
        });
    }

}
