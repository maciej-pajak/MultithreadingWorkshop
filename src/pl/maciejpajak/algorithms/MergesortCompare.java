package pl.maciejpajak.algorithms;

import java.util.Random;

public class MergesortCompare {

    private static Random random = new Random();
    
    public static void main(String[] args) {
        
        long start;
        long stop;
        
        System.out.println("Generating array (sequential)...");
        start = System.currentTimeMillis();
        Integer[] array = createRandomArray(10000000);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");

        System.out.println("Parallel sorting...");
        start = System.currentTimeMillis();
        Mergesort.parallelSort(array);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");
        
        System.out.println("Sequential sorting...");
        start = System.currentTimeMillis();
        Mergesort.sort(array);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " ms");
    }
    
    private static Integer[] createRandomArray(int size) {
        Integer[] arr = new Integer[size];
        
        for (int i = 0 ; i < arr.length ; i++) {
            arr[i] = random.nextInt(100000);
        }
        
        return arr;
    }
    
}
