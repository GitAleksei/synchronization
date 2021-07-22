package ru.netology.task2;

import java.util.ArrayDeque;
import java.util.Queue;

public class Shop {
    Seller seller = new Seller(this);
    Queue<Car> cars = new ArrayDeque<>();

    public Car sellCar() throws InterruptedException {
        return seller.sellCar();
    }

    public void acceptCar() {
        seller.receiveCar();
    }

    public Queue<Car> getCars() {
        return cars;
    }
}
