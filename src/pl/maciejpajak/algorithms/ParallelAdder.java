package pl.maciejpajak.algorithms;

public class ParallelAdder extends Thread {

    private int paritalSum = 0;
    private int[] array;
    private int low;
    private int high;
    
    public ParallelAdder(int[] array, int low, int high) {
        super();
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    public void run() {
        for (int i = low ; i < high; i++) {
            paritalSum += array[i];
        }
    }
    
    public int getPartialSum() {
        return this.paritalSum;
    }
}
