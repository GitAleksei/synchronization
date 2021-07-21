package ru.netology.task1;

import java.util.ArrayDeque;
import java.util.Queue;

public class Shop {
    Seller seller = new Seller(this);
    Queue<Car> cars = new ArrayDeque<>();
    private int producedCars = 0;

    public Car sellCar() {
        return seller.sellCar();
    }

    public void acceptCar() {
        producedCars++;
        seller.receiveCar();
    }

    public Queue<Car> getCars() {
        return cars;
    }

    public int getProducedCars() {
        return producedCars;
    }
}
