package com.company.diningphilosophers;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
    private long id;
    private Lock lock = new ReentrantLock();

    public ChopStick(long id) {
        this.id = id;
    }

    public boolean pickUp(Philosopher philosopher) throws InterruptedException {
        if(lock.tryLock(15, TimeUnit.MILLISECONDS)) {
            System.out.println(philosopher + " picked " + this);
            return true;
        }
        return false;
    }

    public void putDown(Philosopher philosopher) {
        lock.unlock();
        System.out.println(philosopher + " put down " + this);
    }

    @Override
    public String toString() {
        return "ChopStick{" +
                "id=" + id +
                '}';
    }
}
