package entities;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;


public class Customer {

    private final int customerID;
    int arrivalTime;
    private int serviceTime;

    Customer(int customerID, int arrivalTime, int serviceTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    int getServiceTime() {
        return this.serviceTime;
    }

    int getCustomerID() {
        return this.customerID;
    }

    public StackPane toVisual() {
        Circle circle = new Circle(10, 10, 20);
        circle.setFill(Color.PEACHPUFF);
        Text text = new Text(String.valueOf(this.getCustomerID()));
        text.setBoundsType(TextBoundsType.VISUAL);
        StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, text);
        return stack;
    }

}
