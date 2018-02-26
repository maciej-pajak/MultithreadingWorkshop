package pl.maciejpajak.forkjoin.examples;

import java.util.concurrent.ForkJoinPool;

public class RecursiveTaskExample {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        System.out.println( pool.invoke(new SimpleRecursiveTask(1000)) );
    }
}
