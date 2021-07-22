package ru.netology.task3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hall {
    Restaurant restaurant;
    private final Lock lockVisitorToWaiter;
    private final Lock lockWaiterToCook;
    private final Condition order;
    private final Condition orderToCook;
    private final Condition freeWaiter;
    private final Condition dishToWaiter;
    private final Condition dishToVisitor;
    private final Condition dishReady;
    private boolean isDishNotReady;

    public Hall(Restaurant restaurant) {
        this.restaurant = restaurant;
        lockVisitorToWaiter = new ReentrantLock(false);
        lockWaiterToCook = new ReentrantLock(false);
        order = lockVisitorToWaiter.newCondition();
        dishToVisitor = lockVisitorToWaiter.newCondition();
        freeWaiter = lockVisitorToWaiter.newCondition();
        dishToWaiter = lockWaiterToCook.newCondition();
        orderToCook = lockWaiterToCook.newCondition();
        dishReady = lockWaiterToCook.newCondition();
        isDishNotReady = false;
    }

    public void cookingDish() throws InterruptedException {
        lockWaiterToCook.lock();
        try {
            orderToCook.await();
            System.out.println(Thread.currentThread().getName() + " готовит блюдо");
            Thread.sleep(Main.COOKING_TIME);
            System.out.println(Thread.currentThread().getName() + " приготовил блюдо");
            dishToWaiter.signal();
        } finally {
            lockWaiterToCook.unlock();
        }
    }

    public void processingOrder() throws InterruptedException {
        lockVisitorToWaiter.lock();
        try {
            order.await();
            System.out.println(Thread.currentThread().getName() + " взял заказ");
        } finally {
            lockVisitorToWaiter.unlock();
        }

        lockWaiterToCook.lock();
        try {
            while (isDishNotReady) {
                System.out.println(Thread.currentThread().getName() + " не проходит к повару");
                dishReady.await();
            }
            isDishNotReady = true;
            System.out.println(Thread.currentThread().getName() + " несет заказ к повару");
            orderToCook.signal();
            dishToWaiter.await();
            System.out.println(Thread.currentThread().getName() + " несет заказ");
            isDishNotReady = false;
            dishReady.signal();
        } finally {
            lockWaiterToCook.unlock();
        }

        lockVisitorToWaiter.lock();
        try {
            dishToVisitor.signal();
        } finally {
            lockVisitorToWaiter.unlock();
        }
    }

    public void takeOrder() throws InterruptedException {
        lockVisitorToWaiter.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " в ресторане");
            while (restaurant.getOrders().size() >= Main.NUMBER_OF_WAITER) {
                System.out.println("Нет свободных официантов");
                freeWaiter.await();
            }
            order.signal();
            restaurant.getOrders().add(new Order());
            dishToVisitor.await();
            System.out.println(Thread.currentThread().getName() + " приступил к еде");
            restaurant.getOrders().poll();
            freeWaiter.signal();
        } finally {
            lockVisitorToWaiter.unlock();
        }
    }
}
