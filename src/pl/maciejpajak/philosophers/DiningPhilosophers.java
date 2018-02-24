package pl.maciejpajak.philosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Dining philosophers - multithreading workshops exercise.
 */
public class DiningPhilosophers {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;
        
        philosophers = new Philosopher[Constans.NO_OF_PHILOSOPHERS];
        Chopstick[] chopsticks = new Chopstick[Constans.NO_OF_CHOPSTICKS];
        
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Chopstick(i + 1);
        }
        
        executorService = Executors.newFixedThreadPool(Constans.NO_OF_PHILOSOPHERS);
        
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(i + 1, chopsticks[i], chopsticks[(i+1) % Constans.NO_OF_CHOPSTICKS]);
            executorService.execute(philosophers[i]);
        }
        
        Thread.sleep(Constans.SIMULATION_RUNNING_TIME);
        
        for (Philosopher p : philosophers) {
            p.setFull(true);
        }

        executorService.shutdown();
        
        while (!executorService.isTerminated()) {
            Thread.sleep(100);
        }
        
        for (Philosopher p : philosophers) {
            System.out.println(p + " ate " + p.getEatingCounter() + " times.");
        }

    }
}
