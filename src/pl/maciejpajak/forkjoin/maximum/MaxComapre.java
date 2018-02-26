package pl.maciejpajak.forkjoin.maximum;

import java.util.Random;
import java.util.concurrent.Callable;

public class MaxComapre {

    public static int THRESHOLD;
    
    public static void main(String[] args) {
        long execTime;
        int loops = 10;
        
        int[] array = createRandomArray(300000000);
        THRESHOLD = array.length / Runtime.getRuntime().availableProcessors();
        
        execTime = measureExecutionTime(() -> ParallelMax.max(array), loops);
        System.out.printf("Parallel average time %d ms%n", execTime / loops);
        
        execTime = measureExecutionTime(() -> SequentialMax.max(array), loops);
        System.out.printf("Sequential average time %d ms%n", execTime / loops);
        
    }
    
    private static int[] createRandomArray(int size) {
        Random random = new Random();
        
        int[] arr = new int[size];
        
        for (int i = 0 ; i < arr.length ; i++) {
            arr[i] = random.nextInt(100000000);
        }
        
        return arr;
    }
    
    private static long measureExecutionTime(Callable<Integer> toRun, int loops) {
        long totalTime = 0;
        long start; 
        
        try {
            for (int i = 0; i < loops; i++) {
                start = System.currentTimeMillis();
                int res = toRun.call();
                totalTime = totalTime + System.currentTimeMillis() - start;
                System.out.println("Max = " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalTime;
    }
    
}
