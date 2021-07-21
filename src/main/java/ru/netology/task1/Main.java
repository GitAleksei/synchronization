package ru.netology.task1;

public class Main {
    public static void main(String[] args) {
        final Shop shop = new Shop();

        new Thread(shop::sellCar, "Покупатель1").start();
        new Thread(shop::sellCar, "Покупатель2").start();
        new Thread(shop::sellCar, "Покупатель3").start();

        new Thread(shop::acceptCar, "Производитель toyota").start();
        new Thread(shop::acceptCar, "Производитель toyota").start();
        new Thread(shop::acceptCar, "Производитель toyota").start();
    }
}
