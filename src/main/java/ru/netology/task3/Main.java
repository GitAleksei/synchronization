package ru.netology.task3;

public class Main {
    static final int COOKING_TIME = 1_000;
    static final int TIMEOUT_VISITOR = 100;
    static final int EATING_TIME = 1_000;
    static final int TIMEOUT_FOR_COOK = 500;
    static final int NUMBER_OF_VISITOR = 5;
    static final int NUMBER_OF_WAITER = 3;

    public static void main(String[] args) {
        ThreadGroup restaurantGroup = new ThreadGroup("restaurantGroup");
        Thread[] visitorThreads = new Thread[NUMBER_OF_VISITOR];
        Restaurant restaurant = new Restaurant();

        new Thread(restaurantGroup, new Cook(restaurant), "Повар").start();
        for (int i = 0; i < NUMBER_OF_WAITER; i++) {
            new Thread(restaurantGroup, new Waiter(restaurant), "Официант" + (i + 1)).start();
        }

        for (int i = 0; i < NUMBER_OF_VISITOR; i++) {
            try {
                Thread.sleep(TIMEOUT_VISITOR);
            } catch (InterruptedException ignored) {
            }
            visitorThreads[i] = new Thread(new Visitor(restaurant), "Посетитель" + (i + 1));
            visitorThreads[i].start();
        }

        try {
            for (Thread thread : visitorThreads) {
                thread.join();
            }
        } catch (InterruptedException ignored) {
        }

        restaurantGroup.interrupt();
    }
}
