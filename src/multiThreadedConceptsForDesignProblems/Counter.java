package multiThreadedConceptsForDesignProblems;

public class Counter {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }
//    Mutual exclusion being achieved here using the synchronized keyword here.
//    this is treated as a critical section code in the syncronization examples.
//    Intrinsic Lock :-
//    and Explicit Lock.

    public synchronized void increment () {
        counter++;
    }
}
