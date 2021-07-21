package ru.netology.task1;

import java.util.ArrayDeque;
import java.util.Queue;

public class Shop {
    private Seller seller = new Seller(this);
    private Queue<Car> cars = new ArrayDeque<>();

    public Car sellCar() {
        return seller.sellCar();
    }

    public void acceptCar() {
        seller.receiveCar();
    }

    public Queue<Car> getCars() {
        return cars;
    }
}
