package ru.netology.task3;

public class Cook implements Runnable {
    Restaurant restaurant;

    public Cook(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " на работе!");
            while (!Thread.currentThread().isInterrupted()) {
                restaurant.cookingDish();
                Thread.sleep(Main.TIMEOUT_FOR_COOK);
            }
        } catch (InterruptedException ignored) {
        }
    }
}
