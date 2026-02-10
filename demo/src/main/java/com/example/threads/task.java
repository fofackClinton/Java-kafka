package com.example.threads;

public class task  implements Runnable {
    private final String name;

    private TaskCalculator calculator;

    public task(String name, TaskCalculator calculator) {
        this.name = name;
        this.calculator = calculator;
    }

    @Override
    public void run() {
        System.out.println("Task " + name + " is running on thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 1000; i++) {
            calculator.increment();
        }
        System.out.println("Task " + name + " is finished on thread: " + Thread.currentThread().getName());
    }

}
