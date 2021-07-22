package ru.netology.task3;

public class Waiter implements Runnable {
    Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " на работе!");
            while (!Thread.currentThread().isInterrupted()) {
                restaurant.processingOrder();
            }
        } catch (InterruptedException ignored) {
        }
    }
}
