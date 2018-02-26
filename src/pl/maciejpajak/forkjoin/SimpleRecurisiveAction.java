package pl.maciejpajak.forkjoin;

import java.util.concurrent.RecursiveAction;

public class SimpleRecurisiveAction extends RecursiveAction {

    private int simulatedWork;
    
    public SimpleRecurisiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }
    
    @Override
    protected void compute() {
        
        if (simulatedWork > 100) {
            System.out.println("Parallel execution and split task... " + simulatedWork);
            
            SimpleRecurisiveAction action1 = new SimpleRecurisiveAction(simulatedWork / 2);
            SimpleRecurisiveAction action2 = new SimpleRecurisiveAction(simulatedWork / 2);
            
            action1.fork();
            action2.fork();
        } else {
            System.out.println("No need for parallel execution, sequential is ok... " + simulatedWork);
        }
        
    }

}
