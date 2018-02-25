package pl.maciejpajak.algorithms;

public class Sum {
    
    public static int sequentialSum(int[] a) {
        int sum = 0;
        for (int i = 0 ; i < a.length ; i++) {
            sum += a[i];
        }
        return sum;
    }
    
    public static int parallelSum(int[] a) {
        
        int threadsNum = Runtime.getRuntime().availableProcessors();
        
        int step = (int) Math.ceil(a.length * 1.0 / threadsNum);
        
        ParallelAdder[] threads = new ParallelAdder[threadsNum];
        
        for (int i = 0 ; i < threadsNum ; i++) {
            threads[i] = new ParallelAdder(a, i * step, (i + 1) * step);
            threads[i].start();
        }
        
            try {
                for (Thread t : threads) {
                t.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
        int res = 0;

        for (ParallelAdder w : threads) {
            res += w.getPartialSum();
        }

        return res;
    }

}
