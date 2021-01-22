package com.company.diningphilosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhilosophersMain {
    private static final int NUMBER_OF_PHILOSOPHERS = 5;
    private static final int NUMBER_OF_CHOPSTICKS = 5;
    private static final int SIMULATION_TIME = 6000;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS);
        List<Philosopher> philosophers = new ArrayList<>();

        try {
            List<ChopStick> chopSticks = new ArrayList<>();

            for (int i = 0; i < NUMBER_OF_CHOPSTICKS; i++) {
                chopSticks.add(new ChopStick(i));
            }

            for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers.add(new Philosopher(i, chopSticks.get(i), chopSticks.get((i + 1) % NUMBER_OF_CHOPSTICKS)));
                service.execute(philosophers.get(i));
            }

            Thread.sleep(SIMULATION_TIME);

            for(Philosopher p : philosophers) {
                p.setFull(true);
            }

        } finally {
            service.shutdown();
            while(!service.isTerminated()) {
                Thread.sleep(1000);
            }

            for(Philosopher p : philosophers) {
                System.out.println(p + " eats " + p.getEatingCounter() + " times");
            }

        }

    }
}
