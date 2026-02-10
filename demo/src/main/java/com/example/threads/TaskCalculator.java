package com.example.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskCalculator {
    private  int total =0;
    private final Lock lock = new ReentrantLock();

    public void increment(){
        lock.lock();
        try {
            total++;
        } finally {
            lock.unlock();
        }
    }

    public int getTotal() {
        return total;
    }

    public void displaytotal(){
        System.out.println("Total: " + total);
    }

}
