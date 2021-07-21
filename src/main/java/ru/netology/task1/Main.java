package ru.netology.task1;

import java.util.concurrent.ExecutorService;

public class Main {
    static final int NUMBER_OF_CARS = 4;
    static final int CAR_PRODUCTION_TIME = 2_000;
    static final int WAITING_TIME = 1_500;

    public static void main(String[] args) {
        ThreadGroup consumerGroup = new ThreadGroup("consumerGroup");
        final Shop shop = new Shop();

        Thread producerThread = new Thread(new Producer(shop), "Производитель toyota");
        producerThread.start();

        new Thread(consumerGroup, new Consumer(shop), "Покупатель1").start();
        new Thread(consumerGroup, new Consumer(shop), "Покупатель2").start();
        new Thread(consumerGroup, new Consumer(shop), "Покупатель3").start();

        try {
            producerThread.join();
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
        consumerGroup.destroy();
    }
}
