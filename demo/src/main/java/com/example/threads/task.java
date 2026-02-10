package com.example.threads;

public class task  implements Runnable {
    private final String name;

    public task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Task " + name + " is running on thread: " + Thread.currentThread().getName());
    }

}
