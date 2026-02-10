package com.example.threads;

public class TaskCalculator {
    private  int total =0;
    private final  Object lock = new Object();

    public void increment(){
            total++;

    }

    public int getTotal() {
        synchronized (lock) {
            return total;
        }
        
    }

    public void displaytotal(){
        System.out.println("Total: " + total);
    }

}
