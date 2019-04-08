package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


class ViewMain {
    static BorderPane mainInterfaceUI() {

        GridPane newGrid = new GridPane();
        newGrid.setHgap(10);
        newGrid.setVgap(10);
        newGrid.setPadding(new Insets(10, 10, 10, 10));
        TextField ArrivalText1 = new TextField();
        Label ArrivalLabel1 = new Label("Customer's arrival time: ");
        TextField ArrivalText2 = new TextField();
        TextField ServiceText1 = new TextField();
        Label ServiceLabel1 = new Label("Customer's service time: ");
        TextField ServiceText2 = new TextField();

        Label queuesLabel = new Label("Number of queues: ");
        TextField queuesText = new TextField();
        Label customersLabel = new Label("Number of customers: ");
        TextField customersText = new TextField();
        Label simulationLabel = new Label("Simulation Interval: ");
        TextField simulationText = new TextField();
        Label specifiedInterval = new Label("Specified Interval: ");
        TextField specifiedTextFrom = new TextField();
        TextField specifiedTextTo = new TextField();

        Label toLabel1 = new Label(" to ");
        Label toLabel2 = new Label(" to ");
        Label toLabel3 = new Label(" to ");

        ArrivalText1.setMaxWidth(50);
        ArrivalText2.setMaxWidth(50);
        specifiedTextTo.setMaxWidth(50);
        specifiedTextFrom.setMaxWidth(50);
        ServiceText1.setMaxWidth(50);
        ServiceText2.setMaxWidth(50);
        queuesText.setMaxWidth(50);
        customersText.setMaxWidth(50);
        simulationText.setMaxWidth(50);

        Region gridRegionH = new Region();
        gridRegionH.setPrefHeight(10);
        VBox.setVgrow(gridRegionH, Priority.ALWAYS);

        Region gridRegionW = new Region();
        gridRegionW.setPrefWidth(40);
        VBox.setVgrow(gridRegionW, Priority.ALWAYS);

        Button generateBtn = new Button("Start Simulation");
        generateBtn.setOnAction(e -> {
            if (
                    ArrivalText1.getText().matches("[0-9]+") && ArrivalText1.getText().length() > 0 &&
                            ArrivalText2.getText().matches("[0-9]+") && ArrivalText2.getText().length() > 0 &&
                            ServiceText1.getText().matches("[0-9]+") && ServiceText1.getText().length() > 0 &&
                            ServiceText2.getText().matches("[0-9]+") && ServiceText2.getText().length() > 0 &&
                            specifiedTextTo.getText().matches("[0-9]+") && ServiceText1.getText().length() > 0 &&
                            specifiedTextFrom.getText().matches("[0-9]+") && ServiceText2.getText().length() > 0 &&
                            queuesText.getText().matches("[0-9]+") && queuesText.getText().length() > 0 &&
                            simulationText.getText().matches("[0-9]+") && simulationText.getText().length() > 0)
                if (Model.checkInput(Integer.parseInt(ArrivalText1.getText()), Integer.parseInt(ArrivalText2.getText()), Integer.parseInt(ServiceText1.getText()), Integer.parseInt(ServiceText2.getText()), Integer.parseInt(queuesText.getText()), Integer.parseInt(simulationText.getText()), Integer.parseInt(specifiedTextFrom.getText()), Integer.parseInt(specifiedTextTo.getText())) == 1) {
                    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Model.scene2 = new Scene(ViewProcess.processInterfaceUI(), 1200, 800);

                    window.setScene(Model.scene2);
                    Model.initialize();
                } else Utils.alert("ERROR", "You've entered an incorrect input.", "Ok");
            else Utils.alert("ERROR", "You've entered an incorrect input.", "Ok");

        });
        generateBtn.setAlignment(Pos.CENTER);

        newGrid.add(ArrivalLabel1, 1, 1);
        newGrid.add(ArrivalText1, 2, 1);
        newGrid.add(toLabel1, 3, 1);
        newGrid.add(ArrivalText2, 4, 1);
        newGrid.add(ServiceLabel1, 1, 2);
        newGrid.add(ServiceText1, 2, 2);
        newGrid.add(toLabel2, 3, 2);
        newGrid.add(ServiceText2, 4, 2);
        newGrid.add(queuesLabel, 1, 3);
        newGrid.add(queuesText, 2, 3);
        newGrid.add(simulationLabel, 1, 4);
        newGrid.add(simulationText, 2, 4);
        newGrid.add(gridRegionH, 0, 5);
        newGrid.add(specifiedInterval, 1, 6);
        newGrid.add(specifiedTextFrom, 2, 6);
        newGrid.add(toLabel3, 3, 6);
        newGrid.add(specifiedTextTo, 4, 6);

        newGrid.setBackground(new Background(new BackgroundFill(Color.rgb(35, 45, 55), CornerRadii.EMPTY, Insets.EMPTY)));
        Region hBoxRegion = new Region();
        hBoxRegion.setPrefWidth(200);
        HBox.setHgrow(hBoxRegion, Priority.ALWAYS);

        Region wBoxRegion = new Region();
        wBoxRegion.setPrefWidth(200);
        HBox.setHgrow(wBoxRegion, Priority.ALWAYS);

        VBox leftMenu = new VBox(10);

        leftMenu.getChildren().addAll(hBoxRegion);
        leftMenu.setAlignment(Pos.CENTER);

        VBox rightMenu = new VBox(10);


        VBox newCenterPane = new VBox();
        newCenterPane.getChildren().addAll(newGrid, generateBtn);

        newCenterPane.setBackground(new Background(new BackgroundFill(Color.rgb(55, 65, 75), CornerRadii.EMPTY, Insets.EMPTY)));
        newCenterPane.setAlignment(Pos.CENTER);
        rightMenu.getChildren().addAll(wBoxRegion);
        rightMenu.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(newCenterPane);
        borderPane.setLeft(leftMenu);
        borderPane.setRight(rightMenu);

        borderPane.setStyle("-fx-base: rgb(80,91,107);" +
                "    -fx-background: rgb(44,51,61);");

        return borderPane;
    }
}
