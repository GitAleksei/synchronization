package ru.netology.task3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hall {
    Restaurant restaurant;
    private final Lock lock;
    private final Condition order;
    private final Condition orderToCook;
    private final Condition freeWaiter;
    private final Condition dishToWaiter;
    private final Condition dishToVisitor;
    private final Condition cooking;

    public Hall(Restaurant restaurant) {
        this.restaurant = restaurant;
        lock = new ReentrantLock(true);
        order = lock.newCondition();
        dishToVisitor = lock.newCondition();
        freeWaiter = lock.newCondition();
        dishToWaiter = lock.newCondition();
        orderToCook = lock.newCondition();
        cooking = lock.newCondition();
    }

    public void cookingDish() throws InterruptedException {
        lock.lock();
        try {
            while (restaurant.getDishes().size() == 0) {
                orderToCook.await();
            }
            System.out.println(Thread.currentThread().getName() + " готовит блюдо");
            cooking.await(Main.COOKING_TIME, TimeUnit.MILLISECONDS);

            System.out.println(Thread.currentThread().getName() + " приготовил блюдо");
            restaurant.getDishes().poll();
            dishToWaiter.signal();
        } finally {
            lock.unlock();
        }
    }

    public void processingOrder() throws InterruptedException {
        lock.lock();
        try {
            order.await();
            System.out.println(Thread.currentThread().getName() + " взял заказ");
            restaurant.getDishes().add(new Dish());
            orderToCook.signal();

            while (restaurant.getDishes().size() == restaurant.getOrders().size()) {
                dishToWaiter.await();
            }
            System.out.println(Thread.currentThread().getName() + " несет заказ");
            dishToVisitor.signal();
        } finally {
            lock.unlock();
        }
    }

    public void takeOrder() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " в ресторане");
            while (restaurant.getOrders().size() >= Main.NUMBER_OF_WAITER) {
                System.out.println("Нет свободных официантов");
                freeWaiter.await();
            }
            restaurant.getOrders().add(new Order());
            order.signal();

            dishToVisitor.await();
            System.out.println(Thread.currentThread().getName() + " приступил к еде");
            restaurant.getOrders().poll();
            freeWaiter.signal();
        } finally {
            lock.unlock();
        }
    }
}
