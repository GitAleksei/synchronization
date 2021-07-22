package ru.netology.task1;

public class Seller {
    private Shop shop;

    public Seller(Shop shop) {
        this.shop = shop;
    }

    public synchronized void receiveCar() {
        System.out.println(Thread.currentThread().getName() + " выпустил машину");
        shop.getCars().add(new Car());
        notify();
    }

    public synchronized Car sellCar() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
        while (shop.getCars().size() == 0) {
            if (Thread.currentThread().isInterrupted()) {
                return shop.getCars().poll();
            }
            System.out.println("Машин нет\n");
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто\n");

        return shop.getCars().poll();
    }
}
