package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import static gui.Model.*;

class ViewProcess {


    private static VBox leftSide = new VBox();
    private static VBox rightSide = new VBox();
    private static Pane newpp = new Pane();
    private static HBox auxBox = new HBox();
    private static BorderPane borderPane = new BorderPane();
    static HBox mainBox = new HBox();
    private static ScrollPane processLog = new ScrollPane();

    static BorderPane processInterfaceUI() {

        newpp.setMaxWidth(600);
        newpp.setMaxHeight(800);

        processLog.minHeight(800);
        processLog.minWidth(600);
        processLog.setContent(Model.logList);

        newpp.getChildren().addAll(processLog);

        processGrid.setHgap(10);
        processGrid.setVgap(10);
        processGrid.setPadding(new Insets(10, 10, 10, 10));

        leftSide.getChildren().addAll(processGrid);
        leftSide.setPrefHeight(800);
        leftSide.setPrefWidth(400);
        processGrid.setGridLinesVisible(true);
        rightSide.getChildren().addAll(newpp);
        rightSide.setPrefHeight(800);
        auxBox.getChildren().addAll( rightSide);

        HBox leftAuxBox = new HBox();
        Region gridRegionW1 = new Region();
        gridRegionW1.setPrefWidth(100);
        VBox.setVgrow(gridRegionW1, Priority.ALWAYS);
        leftAuxBox.getChildren().addAll(gridRegionW1, leftSide);

        HBox newHorizontal = new HBox();
        newHorizontal.getChildren().addAll(leftAuxBox, auxBox);
        newHorizontal.setPrefHeight(800);

        borderPane.setCenter(newHorizontal);

        borderPane.setStyle("-fx-base: rgb(80,91,107);" +
                "    -fx-background: rgb(44,51,61);");

        return borderPane;
    }

}
