package com.example.threads;

import java.util.concurrent.locks.Lock;

public class TaskCalculator {
    private  int total =0;
    private Lock lock;

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
