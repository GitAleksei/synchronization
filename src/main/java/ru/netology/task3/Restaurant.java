package ru.netology.task3;

import java.util.ArrayDeque;
import java.util.Queue;

public class Restaurant {
    Hall hall = new Hall(this);
    Queue<Order> orders = new ArrayDeque<>();

    public void takeOrder() throws InterruptedException {
        hall.takeOrder();
    }

    public void processingOrder() throws InterruptedException {
        hall.processingOrder();
    }

    public void cookingDish() throws InterruptedException {
        hall.cookingDish();
    }

    public Queue<Order> getOrders() {
        return orders;
    }
}
