package ru.netology.task1;

public class Seller {
    private Shop shop;

    public Seller(Shop shop) {
        this.shop = shop;
    }

    public synchronized void receiveCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " выпустил машину");
            Thread.sleep(1000);
            shop.getCars().add(new Car());
            System.out.println("Продавец: Прием машины завершен");
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (shop.getCars().size() == 0) {
                System.out.println("Продавец: Не могу продать. Машин нет\n");
                wait();
            }
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return shop.getCars().remove();
    }
}
