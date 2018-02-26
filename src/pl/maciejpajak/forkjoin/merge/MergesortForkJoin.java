package pl.maciejpajak.forkjoin.merge;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergesortForkJoin {
    
    private static int THRESHOLD = 1000;
    
    public static <T extends Comparable<T>> void parallelSort(T[] a) {
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[a.length];
        int numOfProcessors = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(numOfProcessors);
        pool.invoke(new MergeSortAction<>(a, aux, 0, a.length));
    }
    
    public static <T extends Comparable<T>> void parallelSortWithThreshold(T[] a, int threshold) {
        THRESHOLD = threshold;
        parallelSort(a);
    }
    
    private static class MergeSortAction<T extends Comparable<T>> extends RecursiveAction {

        private T a[];
        private T aux[];
        private int low;
        private int high;
        
        public MergeSortAction(T[] a, T[] aux, int low, int high) {
            super();
            this.a = a;
            this.aux = aux;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low <= THRESHOLD) {
                sequentialSort(a, aux, low, high);
                return;
            } 
            int mid = (low + high) / 2;
            
            MergeSortAction<T> leftAction = new MergeSortAction<>(a, aux, low, mid);
            MergeSortAction<T> rightAction = new MergeSortAction<>(a, aux, mid, high);
            
            invokeAll(leftAction, rightAction);
            
            merge(a, aux, low, mid, high);
            
        }
        
    }
    
    private static <T extends Comparable<T>> void sequentialSort(T[] a, T[] aux, int low, int high) {
        if (low >= high - 1) return;
        int mid = (low + high) / 2;
        sequentialSort(a, aux, low, mid);
        sequentialSort(a, aux, mid, high);
        merge(a, aux, low, mid, high);
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int low, int mid, int high) {
//        System.out.printf("Merging: low = %d, mid = %d, high = %d%n", low, mid, high);
        for (int i = low ; i < high ; i++) {
            aux[i] = a[i];
        }
        
        int i = low;
        int j = mid;
        for (int k = low ; k < high ; k++) {
            if (i >= mid) {
                a[k] = aux[j++];
            } else if (j >= high) {
                a[k] = aux[i++];
            } else if (isLess(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }
    
    private static <T extends Comparable<T>> boolean isLess(T e, T f) {
        return e.compareTo(f) < 0;
    }
}
