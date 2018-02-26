package pl.maciejpajak.forkjoin.merge;

import java.util.Random;

import pl.maciejpajak.algorithms.Mergesort;

public class MergesortForJoinCompare {

    public static void main(String[] args) {
        
        long start;
        long stop;
        
        System.out.println("Generating array (sequential)...");
        start = System.currentTimeMillis();
        Integer[] array = createRandomArray(10000000);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");

        System.out.println("Sequential sorting...");
        start = System.currentTimeMillis();
        Mergesort.sort(array);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");
        
        System.out.println("Parallel sorting...");
        start = System.currentTimeMillis();
        Mergesort.parallelSort(array);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");
        
        System.out.println("Parallel sorting (fork join, threshold = 10)...");
        start = System.currentTimeMillis();
        MergesortForkJoin.parallelSortWithThreshold(array, 10);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");
        
        System.out.println("Parallel sorting (fork join, threshold = 100)...");
        start = System.currentTimeMillis();
        MergesortForkJoin.parallelSortWithThreshold(array, 100);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");
        
        System.out.println("Parallel sorting (fork join, threshold = 1000)...");
        start = System.currentTimeMillis();
        MergesortForkJoin.parallelSortWithThreshold(array, 1000);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");
        
        System.out.println("Parallel sorting (fork join, threshold = 10000)...");
        start = System.currentTimeMillis();
        MergesortForkJoin.parallelSortWithThreshold(array, 10000);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");
        
        System.out.println("Parallel sorting (fork join, threshold = 100000)...");
        start = System.currentTimeMillis();
        MergesortForkJoin.parallelSortWithThreshold(array, 100000);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");
    }
    
    private static Integer[] createRandomArray(int size) {
        Random random = new Random();
        
        Integer[] arr = new Integer[size];
        
        for (int i = 0 ; i < arr.length ; i++) {
            arr[i] = random.nextInt(100000);
        }
        
        return arr;
    }
}
