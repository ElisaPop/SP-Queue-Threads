package main.control;

import gui.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(Model.scene1);
        Model.alertOnClose(primaryStage);
        primaryStage.setTitle("Queue Simulator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}