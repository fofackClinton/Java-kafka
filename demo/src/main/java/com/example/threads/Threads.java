package com.example.threads;

import java.util.ArrayList;
import java.util.List;

public class Threads {

    public static void main(String[] args) {
        System.out.println("Main thread started: " + Thread.currentThread().getName());
        System.out.println("Active thread count: " + Thread.activeCount());
        System.out.println(Runtime.getRuntime().availableProcessors() + " CPU cores available");

        TaskCalculator calculator = new TaskCalculator();
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new task("Task-" + i, calculator));
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        calculator.displaytotal();
    }

}
