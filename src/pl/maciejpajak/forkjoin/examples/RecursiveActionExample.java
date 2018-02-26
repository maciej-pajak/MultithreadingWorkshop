package pl.maciejpajak.forkjoin.examples;

import java.util.concurrent.ForkJoinPool;

public class RecursiveActionExample {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        forkJoinPool.invoke(new SimpleRecurisiveAction(200));
    }
    
}
