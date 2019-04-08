package entities;

import static entities.Scheduler.queueArray;
import static gui.Model.numberQueues;

public class Executor {

    public static void executeSchedule() {
        Scheduler scheduler = new Scheduler();
        Thread thread = new Thread(scheduler);
        thread.start();
        Thread[] threads = new Thread[numberQueues];
        for (int i = 0; i < numberQueues; i++) {
            threads[i] = new Thread(queueArray[i]);
            threads[i].start();
        }
    }
}