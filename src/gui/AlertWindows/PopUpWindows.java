package gui.AlertWindows;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public  class PopUpWindows {
    private  Boolean confirmBoxAnswer = false;

    public void quickPop(String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(150);
        window.setMinHeight(50);
        Label label = new Label(message);

        window.initStyle(StageStyle.UNDECORATED);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);

        PauseTransition visiblePause = new PauseTransition(Duration.seconds(1));
        visiblePause.setOnFinished(event -> window.close());

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
        visiblePause.play();

    }


    public  Boolean confrimBox(String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(150);
        window.setMinHeight(50);
        Label label = new Label(message);
        Button yesBut = new Button("Yes");
        Button noBut = new Button("No");
        HBox selection = new HBox(10);
        selection.setAlignment(Pos.CENTER);
        selection.getChildren().addAll(yesBut,noBut);

        yesBut.setOnAction(event -> {
            confirmBoxAnswer = true;
            window.close();
        });

        noBut.setOnAction(event -> {window.close();});

        window.initStyle(StageStyle.UNDECORATED);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(label,selection);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return confirmBoxAnswer;
    }
}
