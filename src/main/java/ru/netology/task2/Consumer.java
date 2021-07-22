package ru.netology.task2;

public class Consumer implements Runnable {
    Shop shop;

    public Consumer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(Main.WAITING_TIME);
                shop.sellCar();
            }
        } catch (InterruptedException ignored) {
        }
    }
}
