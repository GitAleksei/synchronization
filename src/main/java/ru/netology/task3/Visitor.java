package ru.netology.task3;

public class Visitor implements Runnable {
    Restaurant restaurant;

    public Visitor(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            restaurant.takeOrder();
            Thread.sleep(Main.EATING_TIME);
            System.out.println(Thread.currentThread().getName() + " вышел из ресторана");
        } catch (InterruptedException ignored) {
        }
    }
}
