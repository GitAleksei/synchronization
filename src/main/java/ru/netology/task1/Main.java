package ru.netology.task1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int NUMBER_OF_CARS = 3;
    static final int CAR_PRODUCTION_TIME = 1_500;
    static final int WAITING_TIME = 2_000;

    public static void main(String[] args) throws InterruptedException {
        final Shop shop = new Shop();

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CARS; i++) {
            threadList.add(new Thread(shop.seller::receiveCar, "Производитель toyota"));
        }
        for (int i = 0; i < NUMBER_OF_CARS; i++) {
            threadList.add(new Thread(shop.seller::sellCar, "Покупатель" + i));
        }

        threadList.forEach(Thread::start);
//        Thread.sleep(1500);
//        threadList.forEach(e -> System.out.println(e.getState() + " " + e.getName()));
//        System.out.println();
//        Thread.sleep(1500);
//        threadList.forEach(e -> System.out.println(e.getState() + " " + e.getName()));
//        System.out.println();
//        Thread.sleep(1500);
//        threadList.forEach(e -> System.out.println(e.getState() + " " + e.getName()));
//        System.out.println();
    }
}
