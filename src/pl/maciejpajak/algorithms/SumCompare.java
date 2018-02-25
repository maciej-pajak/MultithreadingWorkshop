package pl.maciejpajak.algorithms;

import java.util.Random;

public class SumCompare {

    private static Random random = new Random(); 
    
    public static void main(String[] args) {
        int length = 300000000;
        int loops = 10;
        
        long execTime;
        
        System.out.printf("Generating random array (length = %d)...%n%n", length);
        int[] array = createRandomArray(length);

        System.out.println("Starting tests - " + loops + " loops\n");
        
        execTime = measureExecutionTime(() -> Sum.parallelSum(array), loops);
        System.out.printf("Parallel suming time %d ms%n", execTime);
        
        execTime = measureExecutionTime(() -> Sum.sequentialSum(array), loops);
        System.out.printf("Sequential suming time %d ms%n", execTime);

    }
    
    private static int[] createRandomArray(int size) {
        int[] arr = new int[size];
        
        for (int i = 0 ; i < arr.length ; i++) {
            arr[i] = random.nextInt(101) + 1;
        }
        
        return arr;
    }
    
    private static long measureExecutionTime(Runnable toRun, int loops) {
        long totalTime = 0;
        long start; 
        
        try {
            for (int i = 0; i < loops; i++) {
                start = System.currentTimeMillis();
                toRun.run();
                totalTime = totalTime + System.currentTimeMillis() - start;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalTime;
    }
    
}
