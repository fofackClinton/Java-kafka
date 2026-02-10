package com.example.threads;

public class Threads {

    public static void main(String[] args) {
        System.out.println("Main thread started: " + Thread.currentThread().getName());
        System.out.println("Active thread count: " + Thread.activeCount());
        System.out.println(Runtime.getRuntime().availableProcessors() + " CPU cores available");

        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new task("Task-" + i));
            thread.start();
        }
    }

}
