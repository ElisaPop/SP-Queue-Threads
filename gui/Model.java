package gui;

import entities.Executor;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Model {

    static public int serviceMax, serviceMin, arrivalMax, arrivalMin, numberQueues, specifiedFrom, specifiedTo, serviceTime;
    static public int waiting, waitingInt, service, serviceInt, empty;
    static public Scene scene1 = new Scene(ViewMain.mainInterfaceUI(), 800, 600);
    static Scene scene2;
    public static GridPane processGrid = new GridPane();

    public static TextArea logList = new TextArea(" Starting Logs...\n");

    /**
     * This method displays a verification box to see if the user truly wants to close the program or not.
     * This is mainly used if we have something to save before the application is closed, but here it isn't the case.
     * <p>
     * It's a good function to avoid accidental closing
     */

    static private void closeProgram(Stage window) {
        boolean answer = Utils.exitAlert("WARNING", "Are you sure you want to terminate the program?");
        if (answer)
            window.close();
    }

    /**
     * Sets the main scene for the stage that will be called in the main function.
     * It also sets the dimensions of the scene.
     */
    public static Scene mainInterface() {
        return new Scene(ViewMain.mainInterfaceUI(), 800, 600);
    }

    static void addQueues(ObservableList<String> list) {
        for (int i = 0; i < numberQueues; i++) {
            String a = "Queue " + i;
            list.add(a);
            Label lavel = new Label(a);
            lavel.setMinWidth(60);
            processGrid.add(lavel, 0, i);
        }
    }

    public static void alertOnClose(Stage primaryStage) {
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(primaryStage);
        });
    }

    static int checkInput(int arrivalMin, int arrivalMax, int serviceMin, int serviceMax, int numberQueues, int serviceTime, int specifiedFrom, int specifiedTo) {
        if (arrivalMax > arrivalMin && serviceMax > serviceMin) {
            Model.arrivalMax = arrivalMax;
            Model.arrivalMin = arrivalMin;
            Model.serviceMin = serviceMin;
            Model.serviceMax = serviceMax;
            Model.numberQueues = numberQueues;
            Model.serviceTime = serviceTime;
            Model.specifiedFrom = specifiedFrom;
            Model.specifiedTo = specifiedTo;
            return 1;
        }
        return 0;
    }

    static void initialize() {
        Executor.executeSchedule();


    }



}

