package com.company.diningphilosophers;

import java.util.Random;

public class Philosopher implements Runnable {

    private int id;
    private ChopStick leftChopstick;
    private ChopStick rightChopstick;
    private Random random = new Random();
    private int eatingCounter;
    private volatile boolean isFull = false;

    public Philosopher(int id, ChopStick leftChopstick, ChopStick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    @Override
    public void run() {
        try {
            while (!isFull) {
                thinking();
                if (leftChopstick.pickUp(this)) {
                    if(rightChopstick.pickUp(this)) {
                        eating();
                        rightChopstick.putDown(this);
                    }
                    leftChopstick.putDown(this);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void thinking() throws InterruptedException {
        System.out.println(this + " is thinking");
        Thread.sleep(random.nextInt(1000));
    }

    private void eating() throws InterruptedException {
        System.out.println(this + " is eating");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public int getEatingCounter() {
        return eatingCounter;
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
