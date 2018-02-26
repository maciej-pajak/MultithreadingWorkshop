package pl.maciejpajak.forkjoin.maximum;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMax {
    
    private static int THRESHOLD;

    public static int max(int[] a) {
        int numOfProcessors = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(numOfProcessors);
        THRESHOLD = a.length / numOfProcessors;
        return pool.invoke(new MaxFinderTask(a, 0, a.length));
    }
    
    
    private static class MaxFinderTask extends RecursiveTask<Integer> {

        private int[] array;
        private int low;
        private int high;
        
        public MaxFinderTask(int[] array, int low, int high) {
            super();
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Integer compute() {
            if (high - low > THRESHOLD) {
                int mid = (low + high) / 2;
                MaxFinderTask task1 = new MaxFinderTask(array, low, mid);
                MaxFinderTask task2 = new MaxFinderTask(array, mid, high);
                
                invokeAll(task1, task2);
                
                return Math.max(task1.join(), task2.join());
                
            } else {
                return sequentialMax(array, low, high);
            }
        }
        
        private static int sequentialMax(int[] a, int low, int high) {
            int max = a[low];
            for (int i = low + 1 ; i < high ; i++) {
                if (a[i] > max) {
                    max = a[i];
                }
            }
            return max;
        }
        
    }
    
}
