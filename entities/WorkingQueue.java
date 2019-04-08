package entities;

import java.util.LinkedList;
import gui.Model;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static entities.Scheduler.*;
import static gui.Model.*;

public class WorkingQueue implements Runnable {

    private LinkedList<Customer> customers;
    private int queueIndex;
    int waitingAvg1;
    int serviceAvg1;
    int waitingAvg2;
    int serviceAvg2;
    int waitingCnt1;
    int serviceCnt1;
    int waitingCnt2;
    int serviceCnt2;

    WorkingQueue(int queueIndex) {
        this.queueIndex = queueIndex;
        customers = new LinkedList<>();
    }

    @Override
    public void run() {
        while (serviceTime > Scheduler.currentTime || !this.customers.isEmpty()) {
            try {
                if (customers.size() > 0) {
                    Customer currentCustomer = customers.peek();
                    //"Time: " + currentTime + "- Customer #" + Integer.toString(newCustomer.getCustomerID()) + " arrived at Queue @" + Integer.toString(shorte) + "\n");

                    Platform.runLater(() -> Model.logList.appendText("Time: " + Scheduler.currentTime + "- Customer #" + Integer.toString(currentCustomer.getCustomerID()) + " is being served at Queue @" +Integer.toString(this.queueIndex)  + "." + "\n"));
                    System.out.println(Scheduler.currentTime + ": Queue " + Integer.toString(this.queueIndex) + " is currently occupied with Customer " + Integer.toString(currentCustomer.getCustomerID()) + "." + "\n");
                    if(Scheduler.currentTime >= specifiedFrom && Scheduler.currentTime <= specifiedTo)
                    {
                        serviceAvg2 += currentCustomer.getServiceTime();
                        serviceCnt2++;
                    }
                    serviceAvg1 += currentCustomer.getServiceTime();
                    serviceCnt1++;

                    if(customers.size() > 1) {
                        waitingAvg2 += currentCustomer.getServiceTime();
                        waitingCnt2 ++;
                    }
                    waitingAvg1 += currentCustomer.getServiceTime();
                    waitingCnt1 ++;

                    Thread.sleep(currentCustomer.getServiceTime() * 1000);

                    if(peakN > peakNH)
                    {
                        peakNH = peakN;
                        peakHH = currentTime -1;
                    }
                    peakN--;
                    System.out.println(Scheduler.currentTime + ":Queue " + Integer.toString(this.queueIndex) + " finished working with Customer " + Integer.toString(currentCustomer.getCustomerID()) + "." + "\n");
                    Platform.runLater(() -> {
                        //"Time: " + currentTime + "- Customer #" + Integer.toString(newCustomer.getCustomerID()) + " arrived at Queue @" + Integer.toString(shorte) + "\n");

                        Model.logList.appendText("Time: " +Scheduler.currentTime + "- Customer #" +Integer.toString(currentCustomer.getCustomerID())  + " left from Queue @" + Integer.toString(this.queueIndex) + "." + "\n");
                        resetGrid();
                    });
                    customers.removeFirst();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("FINISHED Queue " + Integer.toString(this.queueIndex) + "\n");
    }

    int getLength() {
        return this.customers.size();
    }

    void addCustomer(Customer cust) {
        customers.offer(cust);
    }


    static void resetGrid() {
        processGrid.getChildren().clear();
        for (int i = 0; i < Model.numberQueues; i++) {
            int cnt = 0;

            Label lavel = new Label("Queue " + i);
            lavel.setMinWidth(60);
            processGrid.add(lavel, 0, i);

            for (Customer a : queueArray[i].customers) {
                cnt++;
                processGrid.add(a.toVisual(), cnt, i);
            }
        }
    }

    public void addToGrid(Customer customerItem) {
        processGrid.add(customerItem.toVisual(), this.getLength(), this.queueIndex);
    }

    public Node getNodeByRowColumnIndex(final int row, final int column) {
        Node result = null;
        ObservableList<Node> childrens = processGrid.getChildren();

        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    Boolean isEmpty()
    {
        return customers.isEmpty();
    }
}
