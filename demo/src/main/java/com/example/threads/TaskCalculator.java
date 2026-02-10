package com.example.threads;

public class TaskCalculator {
    private  int total =0;

    public void increment(){
        total++;
    }

    public int getTotal() {
        return total;
    }

    public void displaytotal(){
        System.out.println("Total: " + total);
    }

}
