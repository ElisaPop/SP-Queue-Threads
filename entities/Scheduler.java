package entities;

import gui.Model;
import javafx.application.Platform;

import java.util.*;

import static gui.Model.numberQueues;
import static gui.Model.serviceTime;

public class Scheduler implements Runnable {

    private int customerID;
    static volatile int currentTime;
    static volatile int peakN;
    static volatile int peakHH = 0;
    static volatile int peakNH = 0;
    static int randomStat = 0;
    static int randomTime = 0;

    static WorkingQueue[] queueArray;

    Scheduler() {
        queueArray = new WorkingQueue[numberQueues];
        currentTime = 0;
        customerID = 0;

        for (int i = 0; i < numberQueues; i++) {
            queueArray[i] = new WorkingQueue(i);
        }
    }

    private Boolean isAllQueuesEmpty() {
        for (int i = 0; i < numberQueues; i++)
            if (!queueArray[i].isEmpty()) return false;

        return true;
    }

    private int getShortestQueue() {
        int shortest = queueArray[0].getLength();
        int indexShortest = 0;
        for (int i = 1; i < numberQueues; i++) {
            if (queueArray[i].getLength() < shortest) {
                shortest = queueArray[i].getLength();
                indexShortest = i;
            }
        }
        return indexShortest;
    }

    private void isListsEmpty()
    {
        int addEmpty = 0, cnt = 0;
        for (int i = 0; i < 3 && i < numberQueues; i++) {
            if (queueArray[i].isEmpty()) {
                addEmpty += 1;
                cnt++;
            }
        }
        Model.empty += addEmpty;
    }

    @Override
    public void run() {
        peakHH = 0;
        while (serviceTime > currentTime) {
            Random randomService = new Random();
            randomStat = randomService.nextInt(Model.serviceMax - Model.serviceMin) + Model.serviceMin;
            randomTime = randomService.nextInt(Model.arrivalMax - Model.arrivalMin) + Model.arrivalMin;
            Customer newCustomer = new Customer(customerID++, currentTime, randomStat);
            peakN += 1;
            Platform.runLater(() -> Model.logList.appendText(currentTime + "\n"));
            System.out.println(currentTime);

            int shorte = getShortestQueue();
            queueArray[shorte].addCustomer(newCustomer);
            Platform.runLater(() -> {
                WorkingQueue.resetGrid();
                Model.logList.appendText( currentTime + "- Customer #" + Integer.toString(newCustomer.getCustomerID()) + " " + Integer.toString(newCustomer.getServiceTime()) +"\n");
                Model.logList.appendText("Time: " + currentTime + "- Customer #" + Integer.toString(newCustomer.getCustomerID()) + " arrived at Queue @" + Integer.toString(shorte) + "\n");
            });
            isListsEmpty();
            System.out.println(currentTime + ": Customer " + Integer.toString(newCustomer.getCustomerID()) + " arrived at queue " + Integer.toString(shorte));

            Platform.runLater(WorkingQueue::resetGrid);
            currentTime += randomTime;

            try {
                Thread.sleep(randomTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}
        while (!isAllQueuesEmpty()) {
            try {
                isListsEmpty();
                Thread.sleep(1000);
                currentTime += 1;
                Platform.runLater(() -> Model.logList.appendText(currentTime + "\n"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Process finished. Peak hour:" + peakHH);
        Platform.runLater(() -> computeAverage());
    }

    private static void computeAverage() {
        int addAvg1 = 0, cnt = 0, addAvg2 = 0, addSer1 = 0, addSer2 = 0, addEmpty = 0;

        for (int i = 0; i < 3 && i < numberQueues; i++) {
            if (queueArray[i].waitingCnt1 == 0) queueArray[i].waitingCnt1 = 1;
            if (queueArray[i].waitingCnt2 == 0) queueArray[i].waitingCnt2 = 1;
            if (queueArray[i].serviceAvg1 == 0) queueArray[i].serviceCnt1 = 1;
            if (queueArray[i].serviceAvg2 == 0) queueArray[i].serviceCnt2 = 1;
            if (i < numberQueues) {
                addAvg1 += queueArray[i].waitingAvg1 / queueArray[i].waitingCnt1;
                addAvg2 += queueArray[i].waitingAvg2 / queueArray[i].waitingCnt2;
                addSer1 += queueArray[i].serviceAvg1 / queueArray[i].serviceCnt1;
                addSer2 += queueArray[i].serviceAvg2 / queueArray[i].serviceCnt2;
                cnt++;
            }
        }
        if (cnt != 0) {
            Model.waiting = addAvg1 / cnt;
            Model.waitingInt = addAvg2 / cnt;
            Model.service = addSer1 / cnt;
            Model.serviceInt = addSer2 / cnt;

        }
        Model.empty++;
        Model.logList.appendText("Process finished.\n- Peak hour:" + peakHH + "\n" + "- Average waiting time: " + Model.waiting + "\n - Average waiting time for interval: " + Model.waitingInt + "\n- Average service time: " + Model.service + "\n- Average service time for interval: " + Model.serviceInt + "\n- Empty time: " + Model.empty);
    }
}
