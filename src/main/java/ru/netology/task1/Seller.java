package ru.netology.task1;

public class Seller {
    private Shop shop;

    public Seller(Shop shop) {
        this.shop = shop;
    }

    public synchronized void receiveCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " выпустил машину");
            shop.getCars().add(new Car());
            notify();
            Thread.sleep(Main.CAR_PRODUCTION_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (shop.getCars().size() == 0) {
                System.out.println("Машин нет\n");
                Thread.sleep(Main.WAITING_TIME);
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return shop.getCars().remove();
    }
}
