package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label(message);


        Button yestButton = new Button("yes");
        Button noButton = new Button("no");

        yestButton.setOnAction(event -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(event -> {
            answer= false;
            window.close();
        });



        VBox layout = new VBox(10);
        HBox layout1 = new HBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout1.getChildren().addAll(yestButton,noButton);
        layout1.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label,layout1);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
