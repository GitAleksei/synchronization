package ru.netology.task2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Seller {
    private final Shop shop;
    private final Lock lock;
    private final Condition condition;

    public Seller(Shop shop) {
        this.shop = shop;
        lock = new ReentrantLock(true);
        condition = lock.newCondition();
    }

    public void receiveCar() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " выпустил машину");
            shop.getCars().add(new Car());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public Car sellCar() throws InterruptedException{
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (shop.getCars().size() == 0 && !Thread.currentThread().isInterrupted()) {
                System.out.println("Машин нет\n");
                condition.await();
            }

            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто\n");
            return shop.getCars().poll();
        } finally {
            lock.unlock();
        }
    }
}
