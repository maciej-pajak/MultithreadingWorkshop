package pl.maciejpajak.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class RecursiveActionExample {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        forkJoinPool.invoke(new SimpleRecurisiveAction(200));
    }
    
}
