package ru.netology.task1;

public class Producer implements Runnable {
    Shop shop;

    public Producer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.NUMBER_OF_CARS; i++) {
            shop.acceptCar();
            try {
                Thread.sleep(Main.CAR_PRODUCTION_TIME);
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
    }
}
