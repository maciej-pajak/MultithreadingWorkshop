package pl.maciejpajak.algorithms;

public class Mergesort {

    public static <T extends Comparable<T>> void sort(T[] a) {
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
    
    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid + 1, high);
        merge(a, aux, low, mid, high);
    }
    
    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int low, int mid, int high) {
        for (int i = low ; i <= high ; i++) {
            aux[i] = a[i];
        }
        
        int i = low;
        int j = mid + 1;
        for (int k = low ; k <= high ; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > high) {
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
    
    // ===============
    
    public static <T extends Comparable<T>> void parallelSort(T[] a) {
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[a.length];
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("number of available processors: " + numberOfThreads);
        parallelSort(a, aux, 0, a.length - 1, numberOfThreads);
    }
    
    private static <T extends Comparable<T>> void parallelSort(T[] a, T[] aux, int low, int high, int numOfThreads) {
        if (numOfThreads <= 1) {
            sort(a, aux, low, high);
            return;
        }
        
        int mid = (low + high) / 2;
        
        Thread leftSorter = createParallerSorter(a, aux, low, mid, numOfThreads / 2);
        Thread rightSorter = createParallerSorter(a, aux, low, mid, numOfThreads / 2);
        
        leftSorter.start();
        rightSorter.start();
        
        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        merge(a, aux, low, mid, high);
        
    }
    
    private static <T extends Comparable<T>> Thread createParallerSorter(T[] a, T[] aux, int low, int high, int numOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelSort(a, aux, low, high, numOfThreads);
            }
        };
    }

}
